# Native Integration

## Overview

The native integration layer connects the Java API to `mathlib.so`, a C shared library that performs all mathematical computation. It consists of two classes with distinct responsibilities:

- **`MathLibNative`** — the raw JNA interface. Declares Java method signatures that map directly to C function signatures.
- **`MathLibWrapper`** — a Java-idiomatic wrapper layer. Hides JNA-specific types, manages pointer and memory lifecycles, and presents a clean API to `MathService`.

---

## Technology: JNA (Java Native Access)

JNA allows Java code to call native shared libraries without writing any C glue code (JNI). At runtime, JNA dynamically loads the library and maps Java interface methods to C functions by name, handling type marshalling automatically for primitive types.

The library is loaded via a single static initializer in `MathLibNative`:

```java
MathLibNative INSTANCE = Native.load("mathlib", MathLibNative.class);
```

JNA resolves `"mathlib"` to `libmathlib.so` (on Linux) using the paths in `java.library.path`.

---

## `MathLibNative`

**Package:** `com.amartinsmg.mathlibapi.wrapper`

A `public interface` that extends `com.sun.jna.Library`. Each method declaration maps directly to a C function in the library by matching name and parameter types.

### Type Mapping

JNA handles primitive type conversion automatically:

| Java type | C type |
|-----------|--------|
| `double` | `double` |
| `int` | `int` |
| `long` | `long` (64-bit) |
| `boolean` | `int` (0/1) |
| `double[]` | `double*` (passed as pointer to first element) |
| `Pointer` | `void*` / any pointer return |
| `LongByReference` | `long*` (output parameter) |

### Array inputs

Functions that accept arrays in C (e.g. statistics functions) are declared with a `double[]` parameter and a separate `long length` parameter in the Java interface:

```java
double mean(double[] arr, long length);
double trimmedMean(double[] arr, long length, double percentage);
```

JNA passes `double[]` directly as a pointer to the underlying memory. The `length` must be passed explicitly because C has no way to know the size of a pointer.

### Pointer return values

Two categories of functions return a `Pointer` rather than a primitive:

**Fixed-size arrays** — `midpoint` always returns a 2-element array:
```java
Pointer midpoint(double aX, double aY, double bX, double bY);
```

**Variable-size arrays** — `mode` and `primeFactors` return a heap-allocated array of unknown size. The size is communicated through an output parameter using `LongByReference`:
```java
Pointer primeFactors(long num, LongByReference size);
Pointer mode(double[] arr, long length, LongByReference size);
```

### Memory management

The C library allocates heap memory for pointer return values. The Java side is responsible for freeing it. `MathLibNative` declares a corresponding deallocation function:

```java
void freeArray(Pointer ptr);
```

This must be called after reading all values from a returned `Pointer`. Failure to call it is a memory leak.

---

## `MathLibWrapper`

**Package:** `com.amartinsmg.mathlibapi.wrapper`

`MathLibWrapper` is a class of static methods that wrap `MathLibNative.INSTANCE` calls. Its purpose is to insulate `MathService` from all JNA-specific types (`Pointer`, `LongByReference`) and from the memory management protocol.

### Simple wrappers

For functions that take and return only primitives, the wrapper is a direct pass-through:

```java
public static double circleArea(double radius) {
    return lib.circleArea(radius);
}
```

### Array input wrappers

For functions that take a `double[]` and require an explicit length, the wrapper infers the length from the array:

```java
public static double mean(double[] arr) {
    return lib.mean(arr, arr.length);
}
```

`MathService` passes a plain `double[]`; the wrapper fills in the `length` argument transparently.

### Fixed-size pointer returns — `midpoint`

```java
public static double[] midpoint(double aX, double aY, double bX, double bY) {
    Pointer ptr = lib.midpoint(aX, aY, bX, bY);

    double[] result = new double[2];
    result[0] = ptr.getDouble(0);   // offset 0 bytes  → x
    result[1] = ptr.getDouble(8);   // offset 8 bytes  → y

    lib.freeArray(ptr);

    return result;
}
```

`ptr.getDouble(offset)` reads a `double` at the given byte offset from the pointer. Since `sizeof(double) == 8`, the second element is at offset 8.

### Variable-size pointer returns — `primeFactors` and `mode`

```java
public static long[] primeFactors(long num) {
    LongByReference sizeRef = new LongByReference();
    Pointer ptr = lib.primeFactors(num, sizeRef);
    int size = (int) sizeRef.getValue();

    long[] result = new long[size];
    for (int i = 0; i < size; i++) {
        result[i] = ptr.getLong(i * 8);   // 8 bytes per long
    }

    lib.freeArray(ptr);

    return result;
}
```

`LongByReference` is a JNA type that acts as a `long*` output parameter. After the call, `sizeRef.getValue()` gives the number of elements in the returned array. The wrapper reads each element by byte offset and copies them into a Java `long[]` before freeing the native allocation.

`mode` follows the same pattern with `double` elements (also 8 bytes each):

```java
result[i] = ptr.getDouble(i * 8);
```

---

## Memory Safety Contract

Any method in `MathLibWrapper` that receives a `Pointer` follows this invariant:

1. Read all values from the pointer into a Java array.
2. Call `lib.freeArray(ptr)` **before** returning.
3. Return the Java array (never the raw `Pointer`).

This ensures that no `Pointer` ever escapes `MathLibWrapper`. `MathService` and the rest of the stack work exclusively with standard Java types.
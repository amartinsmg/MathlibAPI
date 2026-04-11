# Architecture

## Overview

MathLib API is a zero-framework HTTP server written in Java that bridges a native C shared library to HTTP clients. The design follows a clear layered separation: request handling, function dispatch, schema management, and native integration are each isolated in their own packages with well-defined responsibilities.

The server is built on top of `com.sun.net.httpserver` — the lightweight HTTP server included in the JDK — and uses JNA (Java Native Access) to call the native `.so` at runtime without any JNI boilerplate.

---

## Layer Diagram

```
┌──────────────────────────────────────────────────────────┐
│                        HTTP Client                        │
└────────────────────────────┬─────────────────────────────┘
                             │ GET /schema  │  POST /exec
                             ▼
┌──────────────────────────────────────────────────────────┐
│                    App.java (entry point)                 │
│         HttpServer · route registration · wiring         │
└──────────┬───────────────────────────────────────────────┘
           │
           ▼
┌──────────────────────────────────────────────────────────┐
│                     RouterHandler                         │
│       HTTP method gating · error serialization           │
└──────────┬───────────────────────────────────────────────┘
           │
           ▼
┌──────────────────────────────────────────────────────────┐
│                       ApiCore                            │
│   orchestrates: schema retrieval + function execution    │
│                                                          │
│   ┌──────────────────┐   ┌──────────────────────────┐   │
│   │  SchemaGenerator │   │    FunctionDispatcher    │   │
│   │  SchemaValidator │   │    FunctionRegistry      │   │
│   │  TypeConverter   │   └──────────────────────────┘   │
│   └──────────────────┘                                   │
└──────────┬───────────────────────────────────────────────┘
           │
           ▼
┌──────────────────────────────────────────────────────────┐
│                     MathService                          │
│   @ApiFunction-annotated static methods                  │
│   business validation · smart return type selection      │
└──────────┬───────────────────────────────────────────────┘
           │
           ▼
┌──────────────────────────────────────────────────────────┐
│                    MathLibWrapper                         │
│   Java-idiomatic wrappers · pointer/array lifecycle      │
└──────────┬───────────────────────────────────────────────┘
           │
           ▼
┌──────────────────────────────────────────────────────────┐
│                    MathLibNative (JNA)                    │
│   JNA interface · maps Java method signatures to C ABI   │
└──────────┬───────────────────────────────────────────────┘
           │  dlopen / dlsym
           ▼
┌──────────────────────────────────────────────────────────┐
│                      mathlib.so (C)                       │
│              the actual mathematical engine               │
└──────────────────────────────────────────────────────────┘
```

---

## Package Structure

```
com.amartinsmg.mathlibapi
│
├── App.java                     Entry point; wires server, routes, and core
│
├── core/
│   ├── ApiCore.java             Facade: schema + execution pipeline
│   ├── TypeConverter.java       JSON→Java type coercion and return normalization
│   ├── dispatcher/
│   │   ├── FunctionDispatcher   Resolves method by name, invokes via reflection
│   │   └── FunctionRegistry     Stores name→Method mappings
│   ├── exceptions/
│   │   ├── ApiException         HTTP-aware exception (carries status code)
│   │   ├── BusinessException    Domain validation errors from service layer
│   │   ├── ConversionException  Type conversion failures
│   │   ├── FunctionNotFoundException
│   │   └── ValidationException  Schema validation failures
│   └── schema/
│       ├── SchemaGenerator      Reads annotations at startup, builds schema map
│       ├── SchemaValidator      Validates incoming args against schema at runtime
│       ├── annotations/
│       │   ├── @ApiFunction     Marks a method as a callable API function
│       │   └── @ApiParam        Annotates a parameter with name, min, and max
│       └── models/
│           ├── FunctionSchema   In-memory representation of a function's schema
│           └── ParamSchema      In-memory representation of a parameter's schema
│
├── handler/
│   └── RouterHandler            HTTP method validation + centralized error handling
│
├── model/
│   └── Point                    Return type for midpoint (x, y coordinates)
│
├── service/
│   └── MathService              All @ApiFunction-annotated service methods
│
├── utils/
│   └── JsonUtils                JSON serialization and deserialization utilities
│
└── wrapper/
    ├── MathLibNative            JNA Library interface to mathlib.so
    └── MathLibWrapper           Java-friendly wrappers over JNA calls
```

---

## Key Design Decisions

**Single execution endpoint.** All functions are called through `POST /exec` using a `{ "fn": "...", "args": {...} }` envelope. This avoids URL proliferation and allows the API surface to grow — new functions in `MathService` become available automatically without touching routing code.

**Annotation-driven registration.** `@ApiFunction` and `@ApiParam` on `MathService` methods are the sole source of truth for both the public schema and runtime validation. There is no separate configuration file or manual registration step.

**Reflection + JNA separation.** Java reflection handles dispatch (calling the right `MathService` method by name); JNA handles native interop (calling the C function). These two concerns are kept in separate layers so that either can be swapped independently.

**Smart numeric returns.** Combinatorial functions (`factorial`, `permutation`, `arrangement`, `combination`) use both a `long`-returning and a `double`-returning native function. The service layer selects automatically: exact integers for small inputs, scientific-notation strings for large results. This avoids silent overflow without sacrificing usability for typical inputs.

**Pointer lifecycle in the wrapper.** Functions that return heap-allocated arrays from C (`midpoint`, `mode`, `primeFactors`) follow a strict pattern in `MathLibWrapper`: receive the `Pointer`, copy values into a Java array, then immediately call `freeArray(ptr)`. This prevents memory leaks across the JNA boundary.

**Error hierarchy.** Three distinct exception types map to different failure modes: `ValidationException` (bad input schema), `BusinessException` (domain rule violation inside the service), and `ApiException` (HTTP-level error). `RouterHandler` converts all of them to structured JSON responses with the appropriate status code.
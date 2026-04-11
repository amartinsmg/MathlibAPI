# Dispatcher

The `dispatcher` sub-package is responsible for two things: maintaining a registry of callable methods and invoking them via reflection. It sits between `ApiCore` and `MathService`, insulating the rest of the system from the Java Reflection API.

---

## `FunctionRegistry`

**Package:** `com.amartinsmg.mathlibapi.core.dispatcher`

`FunctionRegistry` is a thin wrapper around a `Map<String, Method>`. It is populated at startup by scanning a service class for methods annotated with `@ApiFunction`, and provides a simple `get(name)` lookup thereafter.

**Responsibility:** store the mapping from API function name → `java.lang.reflect.Method`.

The function name used as the map key is derived from the `@ApiFunction` annotation: if `name()` is set, that value is used; otherwise the Java method name is used as a fallback.

---

## `FunctionDispatcher`

**Package:** `com.amartinsmg.mathlibapi.core.dispatcher`

`FunctionDispatcher` builds on `FunctionRegistry` and adds two capabilities: resolving a method by name with proper error handling, and invoking it.

### Construction

```java
public FunctionDispatcher(Class<?> clazz)
```

Iterates over `clazz.getDeclaredMethods()`, filters for methods annotated with `@ApiFunction`, and registers each one in the internal registry. This scan happens once at server startup.

### `get(String fn)`

```java
public Method get(String fn)
```

Looks up the `Method` object by function name. Throws `FunctionNotFoundException` (HTTP 404) if no function with that name was registered. This is the first operation executed in `ApiCore.execEngine()`.

### `call(Method m, Object[] args)`

```java
public Object call(Method m, Object[] args)
```

Invokes the resolved `Method` via `Method.invoke(null, args)` — passing `null` as the instance because all `MathService` methods are `static`. Returns the result as `Object`.

Any `InvocationTargetException` is passed up to `ApiCore`, which unwraps it to check for `BusinessException` before deciding how to respond.

---

## Reflection on Static Methods

Because every method in `MathService` is `static`, reflection invocation uses `null` as the receiver:

```java
method.invoke(null, convertedArgs);
```

This is intentional and consistent. The `MathService` class acts as a pure namespace for annotated functions, not as an instantiated service object. This simplifies the registration model — there is no need to manage service instances or handle dependency injection.

---

## Execution Flow (in context)

```
ApiCore.execEngine("circle-area", {"radius": 5.0})
        │
        ├─ FunctionDispatcher.get("circle-area")
        │       └─ FunctionRegistry.lookup("circle-area") → Method
        │
        ├─ SchemaValidator.validate(...)
        │
        ├─ TypeConverter.convertArgs(...)  → Object[] { 5.0 }
        │
        └─ FunctionDispatcher.call(method, Object[] { 5.0 })
                └─ method.invoke(null, 5.0)
                        └─ MathService.circleArea(5.0)
                                └─ MathLibWrapper.circleArea(5.0)
                                        └─ native: circleArea(5.0) → 78.539...
```
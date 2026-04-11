# Core

The `core` package is the engine of the API. It contains everything needed to validate, dispatch, and execute a function call: the `ApiCore` facade, the type conversion pipeline, and the exception hierarchy.

---

## `ApiCore`

**Package:** `com.amartinsmg.mathlibapi.core`

`ApiCore` is the central coordinator. It is instantiated once at server startup with a `Class<?>` argument — the service class whose annotated methods define the API surface — and from that point forwards it handles both schema queries and function execution.

```java
public ApiCore(Class<?> clazz)
```

At construction time, `ApiCore` creates two collaborators:

- `FunctionDispatcher(clazz)` — indexes the class's methods for reflection-based invocation.
- `SchemaGenerator(clazz)` — scans annotations and builds the in-memory schema map.

Neither scans the class again after startup. All subsequent calls read from the pre-built indexes.

### `getSchema()`

```java
public List<Map<String, Object>> getSchema()
```

Returns the full schema as a list of serializable maps, one per registered function. Delegated directly to `SchemaGenerator`. Used by the `GET /schema` route.

### `execEngine(String fn, Map<String, Object> args)`

```java
public Object execEngine(String fn, Map<String, Object> args)
```

The main execution pipeline. Runs in this exact order:

1. **Resolve** — `FunctionDispatcher.get(fn)` looks up the `Method` by function name. Throws `FunctionNotFoundException` if not found.
2. **Validate** — `SchemaValidator.validate(fnSchema, args)` checks that all required parameters are present and within declared `min`/`max` constraints. Throws `ValidationException` on failure.
3. **Convert** — `TypeConverter.convertArgs(fnSchema, args)` coerces the raw JSON values (which arrive as `String`, `Double`, `List`, or `Map`) into the exact Java types the method expects.
4. **Invoke** — `FunctionDispatcher.call(m, convertedArgs)` calls the resolved method via reflection.
5. **Normalize** — `TypeConverter.normalizeReturn(result)` converts the return value to a JSON-serializable form (e.g. `double[]` → `List<Double>`, `Point` → `Map`).

**Exception handling in `execEngine`:**

- If the thrown exception is already an `ApiException`, it is re-thrown as-is.
- If it is an `InvocationTargetException` wrapping a `BusinessException` (thrown from within `MathService`), it is unwrapped and re-wrapped as an `ApiException` with the same status and message.
- Any other exception is caught, logged to `stderr`, and rethrown as a generic `ApiException(500, "Execution error")`.

---

## `TypeConverter`

**Package:** `com.amartinsmg.mathlibapi.core`

Handles the impedance mismatch between the JSON type system (where all numbers are `Double` and arrays are `List`) and the Java method signatures (which may expect `int`, `long`, `double[]`, etc.).

### `convertArgs(FunctionSchema, Map<String, Object>)`

Iterates over the function's `ParamSchema` list in declaration order and converts each argument from its JSON-parsed form to the expected Java type. Returns an `Object[]` ready to be passed to `Method.invoke()`.

### `normalizeReturn(Object)`

Converts return values to JSON-safe types:

- `double[]` → `List<Double>`
- `long[]` → `List<Long>`
- Objects with public fields (e.g. `Point`) → `Map<String, Object>` via reflection on declared fields.
- Primitives and `String` pass through unchanged.

---

## Exception Hierarchy

**Package:** `com.amartinsmg.mathlibapi.core.exceptions`

| Class | Extends | Purpose |
|-------|---------|---------|
| `ApiException` | `RuntimeException` | Carries an HTTP status code. Used as the final, serializable exception at the handler boundary. |
| `BusinessException` | `RuntimeException` | Thrown from `MathService` when a domain rule is violated (e.g. `decimalPlaces < 0`). Converted to `ApiException` by `ApiCore`. |
| `ValidationException` | `ApiException` | Thrown by `SchemaValidator` for missing or out-of-range parameters. |
| `FunctionNotFoundException` | `ApiException` | Thrown by `FunctionDispatcher` when no function matches the requested name. |
| `ConversionException` | `ApiException` | Thrown by `TypeConverter` when a value cannot be coerced to the target type. |

### `ApiException` in detail

```java
public class ApiException extends RuntimeException {
    private final int status;

    public ApiException(int status, String message) { ... }
    public int getStatus() { ... }
}
```

`RouterHandler` catches `ApiException` and uses `getStatus()` to set the HTTP response code, and `getMessage()` as the `"error"` field in the JSON body. All other exception subclasses that extend `ApiException` inherit this behaviour automatically.
# Schema

The `schema` sub-package defines the annotation-driven contract system of the API. It is responsible for two concerns: generating a machine-readable description of all registered functions at startup (`SchemaGenerator`), and validating incoming request arguments against that description at runtime (`SchemaValidator`).

---

## Annotations

### `@ApiFunction`

**Package:** `com.amartinsmg.mathlibapi.core.schema.annotations`  
**Target:** `ElementType.METHOD`  
**Retention:** `RUNTIME`

Marks a method in `MathService` as a callable API function. Methods without this annotation are invisible to the dispatcher and schema system.

| Attribute | Type | Default | Description |
|-----------|------|---------|-------------|
| `name` | `String` | `""` (uses method name) | The public-facing function identifier used in `POST /exec` requests |
| `description` | `String` | `""` | Human-readable description returned by `GET /schema` |
| `namespace` | `String` | `""` (uses `name`) | Logical grouping for the function (e.g. `"area-shape"`, `"statistics"`) |

**Example:**
```java
@ApiFunction(
    name = "circle-area",
    namespace = "area-shape",
    description = "Calculates circle area"
)
public static double circleArea(double radius) { ... }
```

---

### `@ApiParam`

**Package:** `com.amartinsmg.mathlibapi.core.schema.annotations`  
**Target:** `ElementType.PARAMETER`  
**Retention:** `RUNTIME`

Annotates a method parameter to set its public-facing name and optional validation constraints. Parameters without this annotation are included in the schema using their compiled Java name (requires `-parameters` compiler flag to be meaningful).

| Attribute | Type | Default | Description |
|-----------|------|---------|-------------|
| `name` | `String` | `""` (uses parameter name) | The key expected in the `args` JSON object |
| `min` | `double` | `Double.NaN` | Minimum allowed value (inclusive). `NaN` means no lower bound. |
| `max` | `double` | `Double.NaN` | Maximum allowed value (inclusive). `NaN` means no upper bound. |

**Example:**
```java
public static double roundTo(
    double num,
    @ApiParam(name = "decimal-places", min = 0) int decimalPlaces
) { ... }
```

---

## `SchemaGenerator`

**Package:** `com.amartinsmg.mathlibapi.core.schema`

Builds the complete schema map at startup by scanning the service class with reflection. The result is stored in a `Map<String, FunctionSchema>` and never modified afterward — all reads are lookups into this pre-built structure.

### Construction

```java
public SchemaGenerator(Class<?> clazz)
```

Iterates over `clazz.getDeclaredMethods()`. For each method annotated with `@ApiFunction`, it constructs a `FunctionSchema` by:

1. Reading `name`, `description`, and `namespace` from the annotation.
2. Calling `formattType(m.getReturnType())` to produce a schema type descriptor.
3. Calling `formattParameters(m)` to produce a list of `ParamSchema` objects.

### `getSchema()`

```java
public List<Map<String, Object>> getSchema()
```

Serializes the internal `Map<String, FunctionSchema>` into a `List<Map<String, Object>>` — a structure that `JsonUtils.toJson()` can serialize directly. Used by the `GET /schema` route.

### `getFunctionSchema(String fn)`

```java
public FunctionSchema getFunctionSchema(String fn)
```

Returns the `FunctionSchema` for a given function name. Called by `ApiCore` during `execEngine` to feed both `SchemaValidator` and `TypeConverter`.

### `formattType(Class<?> type)`

A recursive static helper that maps a Java type to its schema representation:

| Java type | Schema value |
|-----------|--------------|
| `int` / `Integer` | `"int32"` |
| `long` / `Long` | `"int64"` |
| `float` / `Float` | `"float"` |
| `double` / `Double` | `"double"` |
| `boolean` / `Boolean` | `"boolean"` |
| `String` | `"string"` |
| Any array (e.g. `double[]`) | `{ "type": "array", "items": <recursive> }` |
| Any other object (e.g. `Point`) | `{ "type": "object", "properties": { field: <recursive>, ... } }` |

For object types, it reflects over `getDeclaredFields()` to generate the `properties` map. This means types like `Point` produce a schema automatically without any manual configuration.

---

## `SchemaValidator`

**Package:** `com.amartinsmg.mathlibapi.core.schema`

Validates the incoming `args` map against the `FunctionSchema` before execution. Throws `ValidationException` (HTTP 422) if any check fails.

### Validation rules

- **Required parameters:** Every parameter declared in the schema must have a corresponding key in `args`. Missing keys are rejected.
- **Min/max constraints:** If `@ApiParam` declares a `min` or `max`, the argument value is checked against that bound. This applies to numeric types only.

Business-level validation (e.g. ensuring `total >= selected` in combinatorics) is **not** handled here — those checks live in `MathService` and throw `BusinessException`.

---

## Schema Models

### `FunctionSchema`

```
name        String         — public function identifier
namespace   String         — logical grouping
description String         — human-readable description
returnType  Object         — schema type descriptor (String or Map)
params      List<ParamSchema>
```

### `ParamSchema`

```
name   String   — public parameter name (from @ApiParam or Java parameter name)
type   Object   — schema type descriptor
min    Number   — lower bound (null if unconstrained)
max    Number   — upper bound (null if unconstrained)
```
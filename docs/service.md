# Service

## Overview

`MathService` is the only class in the `service` package. It acts as the API's function catalog: every public mathematical operation exposed by the server is declared here as a `static` method annotated with `@ApiFunction`.

Its responsibilities are narrow and deliberate:

- Declare the public API surface via annotations.
- Apply business-level validation that cannot be expressed as simple min/max constraints.
- Select the correct native function variant (e.g. exact integer vs. floating-point) and convert its result to the appropriate return type.
- Delegate all computation to `MathLibWrapper`.

`MathService` contains no HTTP handling, no JSON parsing, and no schema logic. It knows nothing about the request lifecycle.

---

## Method Conventions

### Annotations

Every method is annotated with `@ApiFunction`. The `name` attribute sets the public identifier used in `POST /exec` requests (using kebab-case, e.g. `"triangle-area-1"`). The `namespace` attribute groups related functions (e.g. `"area-shape"`, `"statistics"`). Parameters use `@ApiParam` when the Java parameter name is not descriptive or when a min/max constraint is needed.

### Static methods

All methods are `static`. `MathService` is never instantiated — it is a pure namespace. The dispatcher calls methods via `Method.invoke(null, args)`.

### Return types

Most methods return `double`, `long`, `boolean`, or arrays thereof, matching the native library's output types. Two exceptions:

- **`Point`** — `midpoint()` returns a `Point` object (a simple value class with `x` and `y` fields). `TypeConverter.normalizeReturn()` converts it to a `Map<String, Object>` for JSON serialization.
- **`String`** — combinatorics and factorial methods return `String` to handle results that exceed `long` capacity.

---

## Smart Numeric Returns (Combinatorics)

The native library exposes two variants for combinatorics functions:

- An exact `long`-returning variant (e.g. `permutation(int)`) — accurate for `n <= 20`.
- A `double`-returning variant using floating-point arithmetic (e.g. `permutationlf(int)`) — handles arbitrarily large inputs at the cost of precision.

`MathService` selects automatically:

```java
if (num <= 20) {
    return String.valueOf(MathLibWrapper.permutation(num));   // exact long
}

double result = MathLibWrapper.permutationlf(num);

if (result > 1e15) {
    return String.format("%.15e", result);                    // scientific notation
} else {
    return String.valueOf((long) result);                     // fits in long
}
```

This strategy applies to `permutation`, `cycle-permutation`, `arrangement`, `combination`, and `factorial`. The result is always returned as `String` to keep the return type uniform across all input ranges.

---

## Business Validation

`MathService` is responsible for domain rules that go beyond simple range checks. When a rule is violated, it throws `BusinessException`, which `ApiCore` intercepts and converts to an `ApiException`.

Examples from the codebase:

| Function | Validation |
|----------|-----------|
| `round-to` | `decimalPlaces >= 0` |
| `factorial` | `num >= 0` |
| `permutation` | `num >= 0` |
| `cycle-permutation` | `num >= 0` |
| `arrangement` | `total > 0`, `selected >= 0`, `selected <= total` |
| `combination` | `total > 0`, `selected >= 0`, `selected <= total` |
| `poisson` | `x >= 0` |

These checks run after schema validation (which covers `@ApiParam` min/max) and before the native call.

---

## Namespace Map

| Namespace | Functions |
|-----------|-----------|
| `area-shape` | `triangle-area-1/2/3`, `square-area`, `rectangle-area`, `rhombus-area`, `parallelogram-area-1/2`, `trapezoid-area`, `regular-polygon-area`, `circle-area`, `circular-sector-area-1/2`, `ellipse-area` |
| `area-surface` | `cube-area`, `cuboid-area`, `prism-area`, `regular-prism-area`, `pyramid-area`, `regular-pyramid-area`, `cylinder-area`, `cone-area`, `sphere-area` |
| `basic-operations` | `logarithm`, `nth-root`, `round-to` |
| `combinatorics` | `permutation`, `cycle-permutation`, `arrangement`, `combination`, `factorial` |
| `finance` | `simple-growth`, `simple-growth-rate`, `compound-growth`, `compound-growth-rate` |
| `geometry` | `deg-to-rad`, `rad-to-deg`, `distance-points`, `midpoint`, `slope-line`, `inclination-line`, `line-y-intercept`, `distance-point-line`, `circle-perimeter`, `polygon-diagonals`, `convex-polygon-sum-interior-angles`, `regular-polygon-interior-angle`, `convex-polygon-exterior-angle` |
| `number-theory` | `is-armstrong`, `gcd`, `is-happy`, `lcm`, `is-perfect`, `prime-factors`, `is-prime` |
| `percentage` | `n-percent-of-x`, `n-is-what-percent-of-x` |
| `probability` | `binomial`, `poisson`, `gaussian-cdf` |
| `statistics` | `mean`, `trimmed-mean`, `geometric-mean`, `harmonic-mean`, `median`, `mode`, `min`, `max`, `range`, `midrange`, `variance`, `std-dev`, `sample-variance`, `sample-std-dev` |
| `trigonometry` | `hypotenuse`, `side-right-triangle`, `side-triangle-law-of-cos`, `ang-triangle-law-of-cos`, `side-triangle-law-of-sin`, `ang-triangle-law-of-sin` |
| `volume` | `cube-volume`, `cuboid-volume`, `prism-volume`, `regular-prism-volume`, `pyramid-volume`, `regular-pyramid-volume`, `cylinder-volume`, `cone-volume`, `sphere-volume` |


---

## Extending the Service

To add a new function to the API:

1. Add a `static` method to `MathService` with `@ApiFunction` and `@ApiParam` annotations.
2. Add the corresponding native declaration to `MathLibNative`.
3. Add a wrapper method to `MathLibWrapper`.

No changes to routing, schema, or dispatch code are required. The annotation scanner picks up the new method automatically on the next server start.
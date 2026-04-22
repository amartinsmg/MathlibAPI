# Tests

## Overview

The test suite is located in `src/test/java/com/amartinsmg/mathlibapi/` and is organized into six test classes, each targeting a distinct layer of the application. Tests are written with JUnit Jupiter (JUnit 5).

To run all tests:

```bash
mvn test
```

---

## Test Classes

### `FunctionRegistryTest`

Tests the `FunctionRegistry` class in isolation, verifying that annotated methods from `MathService` are correctly indexed at construction time.

| Test | What it verifies |
|------|-----------------|
| `testSquareAreaFunctionExists` | A function registered under the name `"square-area"` resolves to a non-null `Method` |
| `testFactorialFunctionExists` | The `"factorial"` API name correctly maps to the Java method `factorialSmart` |
| `testRectangleArea` | The resolved method can be invoked via reflection and returns the correct result (`3 × 5 = 15.0`) |
| `testLcm` | The resolved method returns the correct `long` result (`lcm(8, 10) = 40`) |

---

### `FunctionDispatcherTest`

Tests `FunctionDispatcher`, which builds on `FunctionRegistry` to add error handling and method invocation.

| Test | What it verifies |
|------|-----------------|
| `shouldFindFunction` | `get("circle-perimeter")` resolves without throwing |
| `shouldThrowsException` | `get("hey-jude")` throws `FunctionNotFoundException` with the message `"Function not found: hey-jude"` |
| `testTriangleArea1` | `call()` invokes `triangle-area-1` with `(3.0, 5.0)` and returns `7.5` as a `Double` |
| `testIsPrime` | `call()` invokes `is-prime` with `37L` and returns `true` as a `Boolean` |

---

### `MathLibWrapperTest`

Integration tests for `MathLibWrapper`, exercising the full JNA → native C path. These tests require `libmathlib.so` to be available at runtime.

| Test | What it verifies |
|------|-----------------|
| `testLogarithm` | `log₂(65536) = 16` |
| `testSphereArea` | Surface area of sphere with radius 5 ≈ `314.159265` |
| `testConeVol` | Volume of cone with radius 3 and height 5 ≈ `47.1238898` |
| `testGcd` | `gcd(88, 55) = 11` |
| `testPrimeFactors` | Prime factorization of `1025640` returns `[2, 2, 2, 3, 3, 5, 7, 11, 37]` |
| `testMean` | Arithmetic mean of `{4,5,8,13,13,15,16,18,20}` ≈ `12.444444444` |
| `testMode` | Mode of `{4,5,8,13,13,15,16,18,20}` returns `[13.0]` |

---

### `MathServiceTest`

Unit tests for `MathService`, focusing on the service layer's business logic: correct delegation to the wrapper, smart numeric return selection, and `BusinessException` behaviour.

| Test | What it verifies |
|------|-----------------|
| `testCubeArea` | Surface area of cube with side 5 = `150.0` |
| `testFactorialSmallNumbers` | `12!` returns the exact string `"479001600"` (fits in `long`) |
| `testFactorialLargeNumbers` | `30!` returns scientific notation `"2.652528598121910e+32"` (exceeds `1e15`) |
| `testCombinationSmallNumbers` | `C(15,12)` returns `"455"` |
| `testCombinationLargeNumbers` | `C(60,6)` returns `"50063860"` |
| `shouldCombinationThrowsException` | `C(10,11)` throws `BusinessException` with message `"selected must be <= total"` |
| `testPoisson` | Poisson probability with `λ=5, x=2` ≈ `0.08422` |

---

### `SchemaGeneratorTest`

Tests `SchemaGenerator`, which scans `@ApiFunction`/`@ApiParam` annotations at startup and builds the in-memory schema.

| Test | What it verifies |
|------|-----------------|
| `shouldGenerateSchema` | `SchemaGenerator` instantiates without error |
| `shoutGetSchema` | `getSchema()` returns a non-empty list |
| `shouldAccessFunction` | `getFunctionSchema("arrangement")` returns the correct namespace (`"combinatorics"`) and description |
| `shouldSaveSchema` | Exports the full schema to `target/schema.json` — only runs when the system property `exportSchema` is set to `true` (see [Schema Export](#schema-export)) |

---

### `ApiCoreTest`

Tests `ApiCore` end-to-end through the full execution pipeline: schema + validation + dispatch + normalization.

| Test | What it verifies |
|------|-----------------|
| `shouldGenerateCore` | `ApiCore` instantiates without error |
| `shouldThrowsException` | Passing a `double` (`10.2`) where `int64` is expected throws `ValidationException` with message `"Invalid argument 'num': expected int64"` |
| `testExecEngine` | `deg-to-rad` with `180.0` returns `π` (≈ `3.14159265`) as a `Double` |
| `testReturnNormalizer` | `midpoint` returns a `Map` with `x=1.5` and `y=2.5` — verifying that `TypeConverter.normalizeReturn` correctly converts the `Point` object |

---

### `AppTest`

End-to-end HTTP integration tests. A real `HttpServer` is started on a random port in `@BeforeAll` and stopped in `@AfterAll`. Tests are run against actual HTTP requests and responses.

| Test | What it verifies |
|------|-----------------|
| `contractTests` | A **dynamic test is generated for every function registered in the schema** — each one sends a `POST /exec` request with auto-generated valid arguments and asserts a `200 OK` response |

The `contractTests` method uses `@TestFactory` to build the test list at runtime from `core.getSchema()`, so it automatically covers any new function added to `MathService` without requiring changes to the test class.

**Argument generation** is handled by `generateValue()`, which produces valid default values for each parameter type:

| Schema type | Generated value |
|-------------|----------------|
| `int32`, `int64` | `min` value if declared, otherwise `1` |
| `float`, `double` | `min` value if declared, otherwise `1.0` |
| `string` | `"test"` |
| `boolean` | `true` |
| `array` | List of 3 generated items of the element type |
| `object` | Map with each property filled by its type |

This means `@ApiParam(min = ...)` constraints declared in `MathService` are directly reflected in the contract tests — a parameter with `min = 3` will receive `3` as input, ensuring the test stays within valid boundaries.

---

## Schema Export

The schema for the current version (**0.1**) is already pre-generated and available at `docs/schema.json`. You only need to follow the steps below if you modify the project's entities and need to update the schema documentation.

The `SchemaGeneratorTest` includes a specific test (`shouldSaveSchema`) that exports the full schema. This test is skipped by default and only runs when the JVM system property `exportSchema` is set to `true`:

```bash
mvn test -DexportSchema=true
```

The output file at `target/schema.json` can then be copied to `docs/`:

```bash
cp target/schema.json docs/schema.json
```
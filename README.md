# MathLib API

A lightweight HTTP API that exposes a comprehensive mathematical function library written in C, consumed via JNA (Java Native Access). Built with plain Java using the built-in `com.sun.net.httpserver` — no frameworks required.

---

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Building & Running](#building--running)
- [API Reference](#api-reference)
  - [Endpoints](#endpoints)
  - [Request Format](#request-format)
  - [Response Format](#response-format)
  - [Error Format](#error-format)
- [Namespaces](#namespaces)
- [Type System](#type-system)
- [Examples](#examples)
- [Project Structure](#project-structure)

---

## Overview

MathLib API wraps a native C shared library (`.so`) and exposes its functions over HTTP. Each mathematical function is registered via annotations (`@ApiFunction`, `@ApiParam`) and becomes callable through a single `/exec` endpoint. A `/schema` endpoint describes all available functions at runtime.

**Highlights:**
- 80+ mathematical functions across 13 namespaces
- Self-describing schema endpoint — no external docs required to introspect the API
- Large number results (combinatorics, factorials) are returned as strings to avoid overflow
- Array inputs/outputs supported natively via JSON arrays
- Angle values are expected in **radians** unless otherwise noted

---

## Architecture

```
HTTP Request
     │
     ▼
RouterHandler        ← method validation, error serialization
     │
     ▼
ApiCore              ← orchestrates schema + dispatcher
     ├── SchemaGenerator    ← reads @ApiFunction/@ApiParam annotations at startup
     ├── SchemaValidator    ← validates incoming args against schema
     ├── TypeConverter      ← converts JSON types to Java/native types
     └── FunctionDispatcher ← reflective method invocation on MathService
                                      │
                                      ▼
                              MathLibWrapper     ← JNA bindings to .so
                                      │
                                      ▼
                              mathlib.so (C)     ← actual computation
```

---

## Requirements

- Java 17+
- JNA (`com.sun.jna`) on the classpath
- The native shared library (`mathlib.so`) accessible at runtime (e.g., `java.library.path`)

---

## Building & Running

```bash
# Compile (adjust classpath as needed)
javac -cp .:jna.jar -d out src/**/*.java

# Run
java -cp out:jna.jar -Djava.library.path=./lib com.amartinsmg.mathlibapi.App
```

Server starts on **http://localhost:8080**.

---

## API Reference

### Endpoints

| Method | Path      | Description                              |
|--------|-----------|------------------------------------------|
| GET    | `/schema` | Returns the list of all registered functions with their parameter schemas |
| POST   | `/exec`   | Executes a function by name              |

---

### Request Format

`POST /exec`

```json
{
  "fn": "<function-name>",
  "args": {
    "<param-name>": <value>
  }
}
```

Both `fn` and `args` are required. `args` must be a JSON object (key-value map).

---

### Response Format

**Success (`200 OK`):**

```json
{
  "result": <value>
}
```

`result` can be a `number`, `boolean`, `string` (for large integers), or `array`.

**Schema (`200 OK`):**

```json
[
  {
    "name": "circle-area",
    "namespace": "area-shape",
    "description": "Calculates circle area",
    "returnType": "double",
    "params": [
      { "name": "radius", "type": "double" }
    ]
  }
]
```

---

### Error Format

All errors return a JSON object with an `error` field:

```json
{
  "error": "<message>"
}
```

| HTTP Status | Meaning                                        |
|-------------|------------------------------------------------|
| 400         | Malformed request, missing/invalid fields      |
| 404         | Function not found                             |
| 405         | Wrong HTTP method                              |
| 422         | Validation error (e.g. value out of range)     |
| 500         | Internal execution error                       |

---

## Namespaces

Functions are grouped by namespace. Use the `/schema` endpoint to list all functions; the table below summarizes each namespace.

| Namespace          | Description |
|--------------------|-------------|
| `area-shape`       | Area of 2D shapes: triangle, square, rectangle, rhombus, parallelogram, trapezoid, regular polygon, circle, circular sector, ellipse |
| `area-surface`     | Surface area of 3D solids: cube, cuboid, prism, regular prism, pyramid, regular pyramid, cylinder, cone, sphere |
| `basic-operations` | Rounding, nth root, logarithm |
| `combinatorics`    | Permutation, cycle permutation, arrangement, combination, factorial |
| `finance`          | Simple growth, simple growth rate, compound growth, compound growth rate |
| `geometry`         | Degree/radian conversion, distance, midpoint, slope, inclination, y-intercept, point-to-line distance, circle perimeter, polygon properties |
| `number-theory`    | GCD, LCM, prime check, prime factorization, Armstrong, happy, perfect numbers |
| `percentage`       | Percentage of a number, reverse percentage |
| `probability`      | Binomial, Poisson, Gaussian CDF |
| `statistics`       | Mean (arithmetic, trimmed, geometric, harmonic), median, mode, min, max, range, midrange, variance, standard deviation (population and sample) |
| `trigonometry`     | Hypotenuse, right triangle side, Law of Cosines, Law of Sines |
| `volume`           | Volume of: cube, cuboid, prism, regular prism, pyramid, regular pyramid, cylinder, cone, sphere |

> **Note:** The namespaces `number-thory` and `basic-operaions` appear as-is in the source code (minor typos). The schema endpoint reflects these exact strings.

---

## Type System

The schema uses the following type identifiers:

| Schema Type | JSON representation   | Notes |
|-------------|----------------------|-------|
| `double`    | Number               | 64-bit float |
| `float`     | Number               | 32-bit float |
| `int32`     | Number               | 32-bit integer |
| `int64`     | Number               | 64-bit integer |
| `boolean`   | `true` / `false`     | |
| `string`    | String               | Used when numeric result may exceed 64-bit int range |
| `{ "type": "array", "items": <type> }` | JSON array | Used for dataset inputs and multi-value outputs |
| `{ "type": "object", "properties": {...} }` | JSON object | Used for `Point` return (midpoint) |

---

## Examples

### Get all available functions

```bash
curl http://localhost:8080/schema
```

---

### Area of a circle

```bash
curl -X POST http://localhost:8080/exec \
  -H "Content-Type: application/json" \
  -d '{"fn": "circle-area", "args": {"radius": 5}}'
```

```json
{ "result": 78.53981633974483 }
```

---

### Triangle area (Heron's formula)

```bash
curl -X POST http://localhost:8080/exec \
  -H "Content-Type: application/json" \
  -d '{"fn": "triangle-area-3", "args": {"side-a": 3, "side-b": 4, "side-c": 5}}'
```

```json
{ "result": 6.0 }
```

---

### Factorial (large number)

```bash
curl -X POST http://localhost:8080/exec \
  -H "Content-Type: application/json" \
  -d '{"fn": "factorial", "args": {"num": 25}}'
```

```json
{ "result": "1.551121004333099e+25" }
```

---

### Statistical mean

```bash
curl -X POST http://localhost:8080/exec \
  -H "Content-Type: application/json" \
  -d '{"fn": "mean", "args": {"dataset": [4, 8, 15, 16, 23, 42]}}'
```

```json
{ "result": 18.0 }
```

---

### Midpoint between two points

```bash
curl -X POST http://localhost:8080/exec \
  -H "Content-Type: application/json" \
  -d '{"fn": "midpoint", "args": {"a-x": 0, "a-y": 0, "b-x": 4, "b-y": 6}}'
```

```json
{ "result": { "x": 2.0, "y": 3.0 } }
```

---

### Prime factorization

```bash
curl -X POST http://localhost:8080/exec \
  -H "Content-Type: application/json" \
  -d '{"fn": "prime-factors", "args": {"num": 360}}'
```

```json
{ "result": [2, 2, 2, 3, 3, 5] }
```

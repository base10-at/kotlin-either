# Either Kotlin Library

A Kotlin library providing a functional `Either` type for handling computations that can succeed or fail. Inspired by functional programming paradigms, this library allows safe and expressive error handling and transformation of values.

---

## Overview

The `Either` type represents a value of one of two possible types:

- `Success` – indicates a successful computation or state.
- `Failure` – indicates a failure computation or state.

This library provides utility functions for:

- Mapping and transforming values.
- Binding (monadic operations).
- Traversing and collecting multiple `Either` values.
- Recovering from failures.
- Swapping and querying `Either` states.

---

## Features

### Creating `Either` Values

```kotlin
val success: Either<Nothing, Int> = Either.success(42)
val failure: Either<Int, Nothing> = Either.failure(1)
````

### Accessors

```kotlin
val value = success.orNull()          // 42
val failed = failure.failureOrNull()  // 1
val isSuccess = success.isSuccess     // true
val isFailure = failure.isFailure     // true
```

### Mapping Values

```kotlin
val mapped = success.map { it + 1 }       // Either.success(43)
val failedMapped = failure.mapFailure { it + 1 } // Either.failure(2)
```

### Binding (FlatMapping)

```kotlin
val result = success.bind { Either.success(it + 1) }  // Either.success(43)
val failResult = failure.bindFailure { Either.success("Recovered") } // Either.success("Recovered")
```

### Recovering from Failures

```kotlin
val recovered = failure.recover { it * 2 } // Either.success(2)
```

### Swapping

```kotlin
val swapped = success.swap()  // Either.failure(42)
val swappedFail = failure.swap() // Either.success(1)
```

### Either Operations

```kotlin
val value = success.either(
    onSuccess = { it },
    onFailure = { -1 }
) // 42
```

### Traversing Collections

Supports `List`, `Iterable`, `Sequence`, and `Iterator`:

```kotlin
val list = listOf(1, 2, 3)
val traversed = list.traverseApplicative { Either.success(it + 1) } // Either.success(listOf(2, 3, 4))
```

* `applicative()` – traverses all successes or all failures.
* `monadic()` – stops at the first failure.

### Collecting Multiple `Either`s

```kotlin
val eithers = listOf(Either.success(1), Either.failure(2))
val collected = eithers.collectApplicative() // Either.failure(listOf(2))
```

* `applicative()` – collects all successes or all failures.
* `monadic()` – stops at the first failure.
* 
---

## Usage


---

## License

MIT License © 2025



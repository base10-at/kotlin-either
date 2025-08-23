package at.base10.either.value

import at.base10.either.Either

val <S, F> Either<S, F>.count: Int
    get() = either(
        onSuccess = { 1 },
        onFailure = { 0 },
    )
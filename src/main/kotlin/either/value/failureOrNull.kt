package at.base10.either.value

import at.base10.either.Either

fun <S, F> Either<S, F>.failureOrNull(): F? = either(
    onSuccess = { null },
    onFailure = { it }
)
package at.base10.either.value

import at.base10.either.Either

fun <S, F> Either<S, F>.orNull(): S? = either(
    onSuccess = { it },
    onFailure = { null }
)
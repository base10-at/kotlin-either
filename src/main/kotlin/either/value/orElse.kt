package at.base10.either.value

import at.base10.either.Either

fun <S, F> Either<S, F>.orElse(
    onFailure: (F) -> S,
): S = either(
    onSuccess = { it },
    onFailure = onFailure
)
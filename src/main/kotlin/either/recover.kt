package at.base10.either

import at.base10.either.Either.Companion.success

fun <S, F> Either<S, F>.recover(
    onFailure: (F) -> S,
): Either<S, Nothing> = success(orElse(onFailure))

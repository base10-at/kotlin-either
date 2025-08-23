package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.Either.Companion

fun <S, F, S1> Either<S, F>.bind(onSuccess: (S) -> Either<S1, F>): Either<S1, F> =
    bindEither(
        onSuccess = onSuccess,
        onFailure = Companion::failure
    )
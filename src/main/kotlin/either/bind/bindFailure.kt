package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.Either.Companion

fun <S, F, F1> Either<S, F>.bindFailure(
    onFailure: (F) -> Either<S, F1>
): Either<S, F1> = bindEither(
    onSuccess = Companion::success,
    onFailure = onFailure
)
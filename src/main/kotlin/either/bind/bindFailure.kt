package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.success

fun <S, F, F1> Either<S, F>.flatMapFailure(
    onFailure: (F) -> Either<S, F1>
): Either<S, F1> = bindFailure(onFailure)

fun <S, F, F1> Either<S, F>.bindFailure(
    onFailure: (F) -> Either<S, F1>
): Either<S, F1> = bindEither(
    onSuccess = ::success,
    onFailure = onFailure
)
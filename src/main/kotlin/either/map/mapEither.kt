package at.base10.either.map

import at.base10.either.Either
import at.base10.either.bind.bindEither

fun <S, F, S1, F1> Either<S, F>.mapEither(
    onSuccess: (S) -> S1,
    onFailure: (F) -> F1,
): Either<S1, F1> = bindEither(
    { Either.success(onSuccess(it)) },
    { Either.failure(onFailure(it)) }
)
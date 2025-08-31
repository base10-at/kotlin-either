package at.base10.either.map

import at.base10.either.Either
import at.base10.either.bind.bindEither
import at.base10.either.failure
import at.base10.either.success

fun <S, F, S1, F1> Either<S, F>.mapEither(
    onSuccess: (S) -> S1,
    onFailure: (F) -> F1,
): Either<S1, F1> = bindEither(
    { success(onSuccess(it)) },
    { failure(onFailure(it)) }
)
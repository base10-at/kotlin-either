package at.base10.either.transform

import at.base10.either.Either
import at.base10.either.bind.bindEither
import at.base10.either.failure
import at.base10.either.success

fun <S, F> Either<S, F>.swap(): Either<F, S> = bindEither(
    onSuccess = { failure(it) },
    onFailure = { success(it) }
)
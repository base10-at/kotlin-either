package at.base10.either.transform

import at.base10.either.Either
import at.base10.either.Either.Companion.failure
import at.base10.either.Either.Companion.success
import at.base10.either.bind.bindEither

fun <S, F> Either<S, F>.swap(): Either<F, S> = bindEither(
    onSuccess = { failure(it) },
    onFailure = { success(it) }
)
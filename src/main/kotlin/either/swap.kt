package at.base10.either

import at.base10.either.Either.Companion.failure
import at.base10.either.Either.Companion.success

fun <S, F> Either<S, F>.swap(): Either<F, S> = either(
    onSuccess = { failure(it) },
    onFailure = { success(it) }
)
package at.base10.either.value

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Returns `true` if this [Either] is a [Success], or `false` if it is a [Failure].
 *
 * This is a convenience property for checking the state of an [Either].
 *
 * @return `true` if this is a [Success], otherwise `false`.
 */
val <S, F> Either<S, F>.isSuccess: Boolean
    get() = either(
        onSuccess = { true },
        onFailure = { false }
    )
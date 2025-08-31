package at.base10.either.value

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Returns the success value if this [Either] is a [Success], or `null` if it is a [Failure].
 *
 * @return the success value, or `null` if none exists.
 */
fun <S, F> Either<S, F>.orNull(): S? = either(
    onSuccess = { it },
    onFailure = { null }
)
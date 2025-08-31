package at.base10.either.value

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Returns the success value if this [Either] is a [Success], otherwise computes a fallback value
 * from the failure using [onFailure].
 *
 * This guarantees that a non-null success value is always returned.
 *
 * @param onFailure function to provide a fallback value when this is a [Failure].
 * @return the success value if present, otherwise the fallback value.
 */
fun <S, F> Either<S, F>.orElse(
    onFailure: (F) -> S,
): S = either(
    onSuccess = { it },
    onFailure = onFailure
)
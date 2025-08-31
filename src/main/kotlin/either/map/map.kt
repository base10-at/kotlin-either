package at.base10.either.map

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Transforms the success value of this [Either] if it is a [Success].
 *
 * - If this is a [Success], applies [onSuccess] to its value and wraps it in a new [Success].
 * - If this is a [Failure], propagates the failure unchanged.
 *
 * @param onSuccess function to apply if this is a [Success].
 * @return a new [Either] with the transformed success value or the original failure.
 */
fun <S, F, S1> Either<S, F>.map(
    onSuccess: (S) -> S1,
): Either<S1, F> = mapEither(
    onSuccess = onSuccess,
    onFailure = { it }
)
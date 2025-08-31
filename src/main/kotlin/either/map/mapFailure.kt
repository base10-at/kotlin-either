package at.base10.either.map

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Transforms the failure value of this [Either] if it is a [Failure].
 *
 * - If this is a [Failure], applies [onFailure] to its value and wraps it in a new [Failure].
 * - If this is a [Success], propagates the success unchanged.
 *
 * @param onFailure function to apply if this is a [Failure].
 * @return a new [Either] with the transformed failure value or the original success.
 */
fun <S, F, F1> Either<S, F>.mapFailure(
    onFailure: (F) -> F1,
): Either<S, F1> = mapEither(onSuccess = { it }, onFailure = onFailure)
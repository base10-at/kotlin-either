package at.base10.either.map

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.bind.bindEither
import at.base10.either.failure
import at.base10.either.success

/**
 * Transforms the value of this [Either], handling both success and failure cases.
 *
 * - If this is a [Success], applies [onSuccess] to its value and wraps it in a new [Success].
 * - If this is a [Failure], applies [onFailure] to its value and wraps it in a new [Failure].
 *
 * @param onSuccess function to apply if this is a [Success].
 * @param onFailure function to apply if this is a [Failure].
 * @return a new [Either] with the transformed success or failure value.
 */
fun <S, F, S1, F1> Either<S, F>.mapEither(
    onSuccess: (S) -> S1,
    onFailure: (F) -> F1,
): Either<S1, F1> = bindEither(
    { success(onSuccess(it)) },
    { failure(onFailure(it)) }
)
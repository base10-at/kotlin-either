package at.base10.either.transform

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.success
import at.base10.either.value.orElse

/**
 * Recovers from a [Failure] by transforming its value into a [Success].
 *
 * - If this is a [Failure], applies [onFailure] to its value and wraps the result in a [Success].
 * - If this is a [Success], propagates the success unchanged.
 *
 * This guarantees the result is always a [Success], eliminating the failure type.
 *
 * @param onFailure function to apply if this is a [Failure].
 * @return a [Success] containing either the original success value or the recovered value.
 */
fun <S, F> Either<S, F>.recover(
    onFailure: (F) -> S,
): Either<S, Nothing> = success(orElse(onFailure))

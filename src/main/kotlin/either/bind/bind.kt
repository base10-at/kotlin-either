package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.failure

/**
 * An alias for [bind].
 *
 * @see bind
 */
fun <S, F, S1> Either<S, F>.flatMap(onSuccess: (S) -> Either<S1, F>): Either<S1, F> = bind(onSuccess)

/**
 * Chains a computation that returns another [Either] if this is a [Success].
 *
 * - If this is a [Success], applies [onSuccess] to its value and returns the result.
 * - If this is a [Failure], propagates the failure unchanged.
 *
 * @param onSuccess function to apply if this is a [Success].
 * @return the result of [onSuccess] if this is a [Success], otherwise the same [Failure].
 */
fun <S, F, S1> Either<S, F>.bind(onSuccess: (S) -> Either<S1, F>): Either<S1, F> =
    bindEither(
        onSuccess = onSuccess,
        onFailure = ::failure
    )
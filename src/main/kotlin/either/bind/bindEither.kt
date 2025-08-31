package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * An alias for [bindEither].
 *
 * @see bindEither
 */
fun <S, F, S1, F1> Either<S, F>.flatMapEither(
    onSuccess: (S) -> Either<S1, F1>,
    onFailure: (F) -> Either<S1, F1>
): Either<S1, F1> = bindEither(onSuccess = onSuccess, onFailure = onFailure)

/**
 * Chains computations for both success and failure cases.
 *
 * - If this is a [Success], applies [onSuccess] to its value and returns the result.
 * - If this is a [Failure], applies [onFailure] to its value and returns the result.
 *
 * @param onSuccess function to apply if this is a [Success].
 * @param onFailure function to apply if this is a [Failure].
 * @return the result of applying the corresponding function.
 */
fun <S, F, S1, F1> Either<S, F>.bindEither(
    onSuccess: (S) -> Either<S1, F1>,
    onFailure: (F) -> Either<S1, F1>
): Either<S1, F1> = either(
    onSuccess = onSuccess,
    onFailure = onFailure
)
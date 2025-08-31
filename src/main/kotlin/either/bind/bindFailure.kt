package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.success

/**
 * An alias for [bindFailure].
 *
 * @see bindFailure
 */
fun <S, F, F1> Either<S, F>.flatMapFailure(
    onFailure: (F) -> Either<S, F1>
): Either<S, F1> = bindFailure(onFailure)


/**
 * Transforms a [Failure] value by applying the given function, which may also return an [Either].
 *
 * - If this is a [Failure], applies [onFailure] and returns its result.
 * - If this is a [Success], propagates the success unchanged.
 *
 * @param onFailure function to apply if this is a [Failure].
 * @return the result of [onFailure] if this is a [Failure], otherwise the same [Success].
 */
fun <S, F, F1> Either<S, F>.bindFailure(
    onFailure: (F) -> Either<S, F1>
): Either<S, F1> = bindEither(
    onSuccess = ::success,
    onFailure = onFailure
)
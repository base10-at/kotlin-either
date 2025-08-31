package at.base10.either.transform

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.bind.bindEither
import at.base10.either.failure
import at.base10.either.success

/**
 * Swaps the success and failure types of this [Either].
 *
 * - If this is a [Success], returns a [Failure] containing the success value.
 * - If this is a [Failure], returns a [Success] containing the failure value.
 *
 * This is useful when you want to reinterpret what is considered
 * the "success" or "failure" side of an [Either].
 *
 * @return an [Either] where the success and failure values have been exchanged.
 */
fun <S, F> Either<S, F>.swap(): Either<F, S> = bindEither(
    onSuccess = { failure(it) },
    onFailure = { success(it) }
)
package at.base10.either.value

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Returns the number of success values contained in this [Either].
 *
 * - If this is a [Success], the result is `1`.
 * - If this is a [Failure], the result is `0`.
 *
 *
 * @return `1` if this is a [Success], otherwise `0`.
 */
val <S, F> Either<S, F>.count: Int
    get() = either(
        onSuccess = { 1 },
        onFailure = { 0 },
    )
package at.base10.either.value

import at.base10.either.Either

import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Returns `true` if this [Either] is a [Failure], or `false` if it is a [Success].
 *
 * This is a convenience property for checking the state of an [Either].
 *
 * @return `true` if this is a [Failure], otherwise `false`.
 */
val <S, F> Either<S, F>.isFailure: Boolean get() = !this.isSuccess

package at.base10.either.value

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Returns the success value contained in this [Either].
 *
 * This property should only be accessed when it is known that the instance
 * is a [Success]. If accessed on a [Failure], it will throw.
 *
 * Use this when you explicitly need the success value and are certain
 * the [Either] is not a failure.
 *
 * @throws NoSuchElementException if this [Either] is a [Failure].
 * @return the success value of this [Either].
 */
val <S> Either<S, Nothing>.success: S
    get() = either(
        onFailure = { throw NoSuchElementException() },
        onSuccess = { it },
    )
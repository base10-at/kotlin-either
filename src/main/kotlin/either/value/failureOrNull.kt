package at.base10.either.value

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Returns the failure value contained in this [Either].
 *
 * This property should only be accessed when it is known that the instance
 * is a [Failure]. If accessed on a [Success], it will throw.
 *
 * Use this when you explicitly need the failure value and are certain
 * the [Either] is not a success.
 *
 * @throws NoSuchElementException if this [Either] is a [Success].
 * @return the failure value of this [Either].
 */
/**
 * Returns the failure value if this [Either] is a [Failure], or `null` if it is a [Success].
 *
 * @return the failure value, or `null` if none exists.
 */
fun <S, F> Either<S, F>.failureOrNull(): F? = either(
    onSuccess = { null },
    onFailure = { it }
)
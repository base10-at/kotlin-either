package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.value.failureOrNull
import at.base10.either.value.isFailure

/**
 * Returns the value of the first [Failure] in this [List], or `null` if no failures are present.
 *
 * - If at least one element is a [Failure], this returns the failure value of the first such element.
 * - If all elements are [Success], this returns `null`.
 *
 * @receiver a [List] of [Either] values.
 * @return the failure value of the first [Failure], or `null` if none exist.
 */
fun <S, F> List<Either<S, F>>.firstFailure(): F? {
    return find { it.isFailure }?.failureOrNull()
}
package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.value.isSuccess
import at.base10.either.value.orNull

/**
 * Returns the value of the first [Success] in this [List], or `null` if no successes are present.
 *
 * - If at least one element is a [Success], this returns the success value of the first such element.
 * - If all elements are [Failure], this returns `null`.
 *
 * @receiver a [List] of [Either] values.
 * @return the success value of the first [Success], or `null` if none exist.
 */
fun <S, F> List<Either<S, F>>.firstSuccess(): S? {
    return find { it.isSuccess }?.orNull()
}


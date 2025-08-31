package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.failure
import at.base10.either.success

/**
 * Transforms a [Iterable] of [Either] values into a single [Either] of [Iterable],
 * short-circuiting on the first failure.
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [Iterable] of all success values.
 * - If **any element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without consuming the rest
 *   of the sequence.
 *
 * @receiver a [Iterable] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <S, F> Iterable<Either<S, F>>.collectMonadic(): Either<Iterable<S>, F> {
    val successList = mutableListOf<S>()
    for (element in this) {
        when (element) {
            is Failure -> return failure(element.value)
            else -> successList.add((element as Success<S, *>).value)
        }
    }
    return success(successList)
}

/**
 * Transforms a [Iterator] of [Either] values into a single [Either] of [Iterator],
 * short-circuiting on the first failure.
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [Iterator] of all success values.
 * - If **any element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without consuming the rest
 *   of the sequence.
 *
 * @receiver a [Iterator] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <S, F> Iterator<Either<S, F>>.collectMonadic(): Either<Iterator<S>, F> = this.asSequence()
    .asIterable()
    .collectMonadic()
    .mapToIterator()

/**
 * Transforms a [List] of [Either] values into a single [Either] of [List],
 * short-circuiting on the first failure.
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [List] of all success values.
 * - If **any element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without consuming the rest
 *   of the sequence.
 *
 * @receiver a [List] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <S, F> List<Either<S, F>>.collectMonadic(): Either<List<S>, F> = this
    .asIterable()
    .collectMonadic()
    .mapToList()

/**
 * Transforms a [Sequence] of [Either] values into a single [Either] of [Sequence],
 * short-circuiting on the first failure (monadic style).
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [Sequence] of all success values.
 * - If **any element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without consuming the rest
 *   of the sequence.
 *
 * @receiver a [Sequence] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <S, F> Sequence<Either<S, F>>.collectMonadic(): Either<Sequence<S>, F> = this
    .asIterable()
    .collectMonadic()
    .mapToSequence()
package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.failure
import at.base10.either.success

/**
 * Transforms a [Iterable] of [Either] values into a single [Either] of [Iterable].
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [Iterable] of all success values.
 * - If **any element** is a [Failure], this returns a [Failure] containing
 *   a [Iterable] of all failure values.
 *
 * @receiver a [Iterable] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <S, F> Iterable<Either<S, F>>.collectApplicative(): Either<Iterable<S>, Iterable<F>> {
    val pair = partition().mapPairToList()
    val failureList = pair.second
    return when {
        failureList.isNotEmpty() -> failure(failureList)
        else -> success(pair.first)
    }
}

/**
 * Transforms a [Iterator] of [Either] values into a single [Either] of [Iterator].
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [Iterator] of all success values.
 * - If **any element** is a [Failure], this returns a [Failure] containing
 *   a [Iterator] of all failure values.
 *
 * @receiver a [Iterator] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <S, F> Iterator<Either<S, F>>.collectApplicative(): Either<Iterator<S>, Iterator<F>> = this.asSequence()
    .asIterable()
    .collectApplicative()
    .mapEitherToIterator()

/**
 * Transforms a [List] of [Either] values into a single [Either] of [List].
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [List] of all success values.
 * - If **any element** is a [Failure], this returns a [Failure] containing
 *   a [List] of all failure values.
 *
 * @receiver a [List] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <S, F> List<Either<S, F>>.collectApplicative(): Either<List<S>, List<F>> = this
    .asIterable()
    .collectApplicative()
    .mapEitherToList()

/**
 * Transforms a [Sequence] of [Either] values into a single [Either] of [Sequence].
 *
 * - If **all elements** are [Success], this returns a [Success] containing
 *   a [Sequence] of all success values.
 * - If **any element** is a [Failure], this returns a [Failure] containing
 *   a [Sequence] of all failure values.
 *
 * @receiver a [Sequence] of [Either] values.
 * @return a [Success] with all success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <S, F> Sequence<Either<S, F>>.collectApplicative(): Either<Sequence<S>, Sequence<F>> = this
    .asIterable()
    .collectApplicative()
    .mapEitherToSequence()
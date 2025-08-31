package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Partitions this [Iterable] of [Either] values into separate collections of successes and failures.
 *
 * - All [Success] values are collected into the first component of the returned [Pair].
 * - All [Failure] values are collected into the second component of the returned [Pair].
 *
 *
 * @receiver an [Iterable] of [Either] values.
 * @return a [Pair] where the first element is an [Iterable] of all success values,
 *         and the second element is an [Iterable] of all failure values.
 */
fun <S, F> Iterable<Either<S, F>>.partition(): Pair<Iterable<S>, Iterable<F>> {
    val successList = mutableListOf<S>()
    val failureList = mutableListOf<F>()

    for (element in this) {
        when (element) {
            is Failure -> failureList.add(element.value)
            else -> successList.add((element as Success<S, *>).value)
        }
    }
    return Pair(successList, failureList)
}

/**
 * Partitions this [Iterator] of [Either] values into separate collections of successes and failures.
 *
 * - All [Success] values are collected into the first component of the returned [Pair].
 * - All [Failure] values are collected into the second component of the returned [Pair].
 *
 *
 * @receiver an [Iterator] of [Either] values.
 * @return a [Pair] where the first element is an [Iterator] of all success values,
 *         and the second element is an [Iterable] of all failure values.
 */
fun <S, F> Iterator<Either<S, F>>.partition() = this.asSequence()
    .asIterable()
    .partition()
    .mapPairToIterator()

/**
 * Partitions this [List] of [Either] values into separate collections of successes and failures.
 *
 * - All [Success] values are collected into the first component of the returned [Pair].
 * - All [Failure] values are collected into the second component of the returned [Pair].
 *
 *
 * @receiver an [List] of [Either] values.
 * @return a [Pair] where the first element is an [List] of all success values,
 *         and the second element is an [Iterable] of all failure values.
 */
fun <S, F> List<Either<S, F>>.partition() = this
    .asIterable()
    .partition()
    .mapPairToList()

/**
 * Partitions this [Sequence] of [Either] values into separate collections of successes and failures.
 *
 * - All [Success] values are collected into the first component of the returned [Pair].
 * - All [Failure] values are collected into the second component of the returned [Pair].
 *
 *
 * @receiver an [Sequence] of [Either] values.
 * @return a [Pair] where the first element is an [Sequence] of all success values,
 *         and the second element is an [Iterable] of all failure values.
 */
fun <S, F> Sequence<Either<S, F>>.partition() = this
    .asIterable()
    .partition()
    .mapPairToSequence()
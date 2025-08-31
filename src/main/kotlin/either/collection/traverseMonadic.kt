package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Applies [transformer] to each element of this [Iterable], producing a [Iterable] of [Either] values,
 * and then collects the results monadically (short-circuiting on the first failure).
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [Iterable] of all success values.
 * - If **any transformed element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without processing the remaining elements.
 *
 * It is equivalent to mapping with [transformer] followed by [collectMonadic].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [Iterable] of transformed success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <V, S, F> Iterable<V>.traverseMonadic(transformer: (V) -> Either<S, F>): Either<Iterable<S>, F> = this
    .mapAsIterable(transformer)
    .collectMonadic()

/**
 * Applies [transformer] to each element of this [Iterator], producing a [Iterator] of [Either] values,
 * and then collects the results monadically (short-circuiting on the first failure).
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [Iterator] of all success values.
 * - If **any transformed element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without processing the remaining elements.
 *
 * It is equivalent to mapping with [transformer] followed by [collectMonadic].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [Iterator] of transformed success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <V, S, F> Iterator<V>.traverseMonadic(transformer: (V) -> Either<S, F>): Either<Iterator<S>, F> = this
    .asIterable()
    .traverseMonadic(transformer)
    .mapToIterator()

/**
 * Applies [transformer] to each element of this [Sequence], producing a [Sequence] of [Either] values,
 * and then collects the results monadically (short-circuiting on the first failure).
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [Sequence] of all success values.
 * - If **any transformed element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without processing the remaining elements.
 *
 * It is equivalent to mapping with [transformer] followed by [collectMonadic].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [Sequence] of transformed success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <V, S, F> Sequence<V>.traverseMonadic(transformer: (V) -> Either<S, F>): Either<Sequence<S>, F> = this
    .asIterable()
    .traverseMonadic(transformer)
    .mapToSequence()

/**
 * Applies [transformer] to each element of this [List], producing a [List] of [Either] values,
 * and then collects the results monadically (short-circuiting on the first failure).
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [List] of all success values.
 * - If **any transformed element** is a [Failure], this immediately returns a [Failure]
 *   with the first failure value encountered, without processing the remaining elements.
 *
 * It is equivalent to mapping with [transformer] followed by [collectMonadic].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [List] of transformed success values if no failures are present,
 *         otherwise the first [Failure] encountered.
 */
fun <V, S, F> List<V>.traverseMonadic(transformer: (V) -> Either<S, F>): Either<List<S>, F> = this
    .asIterable()
    .traverseMonadic(transformer)
    .mapToList()
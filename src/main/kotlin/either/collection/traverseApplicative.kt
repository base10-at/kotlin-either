package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Applies [transformer] to each element of this [Iterator], producing a [Iterator] of [Either] values,
 * and then collects the results applicatively.
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [Iterator] of all success values.
 * - If **any transformed element** is a [Failure], this returns a [Failure] containing
 *   a [Iterator] of all failure values.
 *
 * It is equivalent to mapping with [transformer] followed by [collectApplicative].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [Iterator] of transformed success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <V, S, F> Iterable<V>.traverseApplicative(transformer: (V) -> Either<S, F>): Either<Iterable<S>, Iterable<F>> = this
    .mapAsIterable(transformer)
    .collectApplicative()

/**
 * Applies [transformer] to each element of this [Iterator], producing a [Iterator] of [Either] values,
 * and then collects the results applicatively.
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [Iterator] of all success values.
 * - If **any transformed element** is a [Failure], this returns a [Failure] containing
 *   a [Iterator] of all failure values.
 *
 * It is equivalent to mapping with
 * [transformer] followed by [collectApplicative].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [Iterator] of transformed success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <V, S, F> Iterator<V>.traverseApplicative(transformer: (V) -> Either<S, F>): Either<Iterator<S>, Iterator<F>> = this
    .asIterable()
    .traverseApplicative(transformer)
    .mapEitherToIterator()

/**
 * Applies [transformer] to each element of this [Sequence], producing a [Sequence] of [Either] values,
 * and then collects the results applicatively.
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [Sequence] of all success values.
 * - If **any transformed element** is a [Failure], this returns a [Failure] containing
 *   a [Sequence] of all failure values.
 *
 * It is equivalent to mapping with
 * [transformer] followed by [collectApplicative].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [Sequence] of transformed success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <V, S, F> Sequence<V>.traverseApplicative(transformer: (V) -> Either<S, F>): Either<Sequence<S>, Sequence<F>> = this
    .asIterable()
    .traverseApplicative(transformer)
    .mapEitherToSequence()

/**
 * Applies [transformer] to each element of this [List], producing a [List] of [Either] values,
 * and then collects the results applicatively.
 *
 * - If **all transformed elements** are [Success], this returns a [Success] containing
 *   a [List] of all success values.
 * - If **any transformed element** is a [Failure], this returns a [Failure] containing
 *   a [List] of all failure values.
 *
 * It is equivalent to mapping with
 * [transformer] followed by [collectApplicative].
 *
 * @param transformer a function that transforms each element [V] into an [Either].
 * @return a [Success] with a [List] of transformed success values if no failures are present,
 *         otherwise a [Failure] with all failure values.
 */
fun <V, S, F> List<V>.traverseApplicative(transformer: (V) -> Either<S, F>): Either<List<S>, List<F>> = this
    .asIterable()
    .traverseApplicative(transformer)
    .mapEitherToList()
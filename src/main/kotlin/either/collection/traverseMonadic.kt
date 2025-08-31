package at.base10.either.collection

import at.base10.either.Either

fun <V, S, F> Iterable<V>.traverseMonadic(transformer: (V) -> Either<S, F>) = this
    .mapAsIterable(transformer)
    .collectMonadic()

fun <V, S, F> Iterator<V>.traverseMonadic(transformer: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseMonadic(transformer)
    .mapToIterator()

fun <V, S, F> Sequence<V>.traverseMonadic(transformer: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseMonadic(transformer)
    .mapToSequence()

fun <V, S, F> List<V>.traverseMonadic(transformer: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseMonadic(transformer)
    .mapToList()
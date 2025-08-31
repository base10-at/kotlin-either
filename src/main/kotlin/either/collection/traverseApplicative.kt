package at.base10.either.collection

import at.base10.either.Either

fun <V, S, F> Iterable<V>.traverseApplicative(transformer: (V) -> Either<S, F>) = this
    .mapAsIterable(transformer)
    .collectApplicative()

fun <V, S, F> Iterator<V>.traverseApplicative(transformer: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseApplicative(transformer)
    .mapEitherToIterator()

fun <V, S, F> Sequence<V>.traverseApplicative(transformer: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseApplicative(transformer)
    .mapEitherToSequence()

fun <V, S, F> List<V>.traverseApplicative(transformer: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseApplicative(transformer)
    .mapEitherToList()
package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.collect.collectApplicative
import at.base10.either.collect.collectMonadic

fun <V, S, F> Iterable<V>.traverseApplicative(mapping: (V) -> Either<S, F>) = this
    .asSequence()
    .map(mapping)
    .asIterable()
    .collectApplicative()

fun <V, S, F> Iterable<V>.traverseMonadic(mapping: (V) -> Either<S, F>) = this
    .asSequence()
    .map(mapping)
    .asIterable()
    .collectMonadic()

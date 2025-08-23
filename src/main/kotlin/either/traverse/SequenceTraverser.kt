package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

fun <V, S, F> Sequence<V>.traverseApplicative(mapping: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseApplicative(mapping)
    .mapEither(
        onSuccess = Iterable<S>::asSequence,
        onFailure = Iterable<F>::asSequence,
    )

fun <V, S, F> Sequence<V>.traverseMonadic(mapping: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseMonadic(mapping)
    .map(Iterable<S>::asSequence)

package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

private fun <V> Iterator<V>.asIterable() =  this
    .asSequence()
    .asIterable()

fun <V, S, F> Iterator<V>.traverseApplicative(mapping: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseApplicative(mapping)
    .mapEither(
        onSuccess = Iterable<S>::iterator,
        onFailure = Iterable<F>::iterator,
    )

fun <V, S, F> Iterator<V>.traverseMonadic(mapping: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseMonadic(mapping)
    .map(Iterable<S>::iterator)

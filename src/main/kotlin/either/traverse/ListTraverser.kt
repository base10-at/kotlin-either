package at.base10.either.traverse

import at.base10.either.Either

@Suppress("UNCHECKED_CAST")
fun <V, S, F> List<V>.traverseApplicative(mapping: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseApplicative(mapping) as Either<List<S>, List<F>>

@Suppress("UNCHECKED_CAST")
fun <V, S, F> List<V>.traverseMonadic(mapping: (V) -> Either<S, F>) = this
    .asIterable()
    .traverseMonadic(mapping) as Either<List<S>, F>

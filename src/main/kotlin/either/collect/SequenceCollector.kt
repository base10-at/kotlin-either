package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

fun <S, F> Sequence<Either<S, F>>.collectApplicative() = this
    .asIterable()
    .collectApplicative()
    .mapEither(
        onSuccess = Iterable<S>::asSequence,
        onFailure = Iterable<F>::asSequence,
    )

fun <S, F> Sequence<Either<S, F>>.collectMonadic() = this
    .asIterable()
    .collectMonadic()
    .map(Iterable<S>::asSequence)

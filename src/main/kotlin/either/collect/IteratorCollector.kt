package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

fun <S, F> Iterator<Either<S, F>>.collectApplicative() = this.asSequence()
    .asIterable()
    .collectApplicative()
    .mapEither(
        onSuccess = Iterable<S>::iterator,
        onFailure = Iterable<F>::iterator,
    )

fun <S, F> Iterator<Either<S, F>>.collectMonadic() = this.asSequence()
    .asIterable()
    .collectMonadic()
    .map(
        onSuccess = Iterable<S>::iterator,
    )

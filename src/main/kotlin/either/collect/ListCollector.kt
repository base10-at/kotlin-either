package at.base10.either.collect

import at.base10.either.Either


@Suppress("UNCHECKED_CAST")
fun <S, F> List<Either<S, F>>.collectApplicative() = this
    .asIterable()
    .collectApplicative() as Either<List<S>, List<F>>

@Suppress("UNCHECKED_CAST")
fun <S, F> List<Either<S, F>>.collectMonadic() = this
    .asIterable()
    .collectMonadic() as Either<List<S>, F>

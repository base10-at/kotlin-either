package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success

fun <S, F> Iterable<Either<S, F>>.collectApplicative(): Either<Iterable<S>, Iterable<F>> {
    val pair = partition().mapPairToList()
    val failureList = pair.second
    return when {
        failureList.isNotEmpty() -> failure(failureList)
        else -> success(pair.first)
    }
}

fun <S, F> Iterator<Either<S, F>>.collectApplicative() = this.asSequence()
    .asIterable()
    .collectApplicative()
    .mapEitherToIterator()

fun <S, F> List<Either<S, F>>.collectApplicative() = this
    .asIterable()
    .collectApplicative()
    .mapEitherToList()

fun <S, F> Sequence<Either<S, F>>.collectApplicative() = this
    .asIterable()
    .collectApplicative()
    .mapEitherToSequence()
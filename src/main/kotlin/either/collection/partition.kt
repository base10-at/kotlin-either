package at.base10.either.collection

import at.base10.either.Either

fun <S, F> Iterable<Either<S, F>>.partition(): Pair<Iterable<S>, Iterable<F>> {
    val successList = mutableListOf<S>()
    val failureList = mutableListOf<F>()

    for (element in this) {
        when (element) {
            is Either.Failure -> failureList.add(element.value)
            else -> successList.add((element as Either.Success<S, *>).value)
        }
    }
    return Pair(successList, failureList)
}

fun <S, F> Iterator<Either<S, F>>.partition() = this.asSequence()
    .asIterable()
    .partition()
    .mapPairToIterator()

fun <S, F> List<Either<S, F>>.partition() = this
    .asIterable()
    .partition()
    .mapPairToList()

fun <S, F> Sequence<Either<S, F>>.partition() = this
    .asIterable()
    .partition()
    .mapPairToSequence()
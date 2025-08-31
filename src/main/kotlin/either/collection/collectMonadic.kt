package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success

fun <S, F> Iterable<Either<S, F>>.collectMonadic(): Either<Iterable<S>, F> {
    val successList = mutableListOf<S>()
    for (element in this) {
        when (element) {
            is Either.Failure -> return failure(element.value)
            else -> successList.add((element as Either.Success<S, *>).value)
        }
    }
    return success(successList)
}

fun <S, F> Iterator<Either<S, F>>.collectMonadic() = this.asSequence()
    .asIterable()
    .collectMonadic()
    .mapToIterator()

fun <S, F> List<Either<S, F>>.collectMonadic() = this
    .asIterable()
    .collectMonadic()
    .mapToList()

fun <S, F> Sequence<Either<S, F>>.collectMonadic() = this
    .asIterable()
    .collectMonadic()
    .mapToSequence()
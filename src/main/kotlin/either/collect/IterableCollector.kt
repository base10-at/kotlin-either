package at.base10.either.collect

import at.base10.either.Either

val <S, F> Iterable<Either<S, F>>.collect: IterableCollector<S, F> get() = IterableCollector(this)

class IterableCollector<S, F>(private val iterable: Iterable<Either<S, F>>) {

    fun applicative(): Either<Iterable<S>, Iterable<F>> {
        val successList = mutableListOf<S>()
        val failureList = mutableListOf<F>()

        for (element in iterable) {
            when (element) {
                is Either.Failure -> failureList.add(element.value)
                else -> successList.add((element as Either.Success<S, *>).value)
            }
        }
        return if (failureList.isNotEmpty()) {
            Either.failure(failureList.toList())
        } else {
            Either.success(successList.toList())
        }
    }

    fun monadic(): Either<Iterable<S>, F> {
        val successList = mutableListOf<S>()
        for (element in iterable) {
            when (element) {
                is Either.Failure -> return Either.failure(element.value)
                else -> successList.add((element as Either.Success<S, *>).value)
            }
        }
        return Either.success(successList)
    }
}
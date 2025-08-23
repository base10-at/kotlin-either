package at.base10.either.collect

import at.base10.either.Either

val <S, F> List<Either<S, F>>.collect: ListCollector<S, F>
    get() = ListCollector(this)

class ListCollector<S, F>(list: List<Either<S, F>>) {

    private val collector: IterableCollector<S, F> = IterableCollector(list)

    @Suppress("UNCHECKED_CAST")
    fun applicative(): Either<List<S>, List<F>> = collector.applicative() as Either<List<S>, List<F>>

    @Suppress("UNCHECKED_CAST")
    fun monadic(): Either<List<S>, F> = collector.monadic() as Either<List<S>, F>

}
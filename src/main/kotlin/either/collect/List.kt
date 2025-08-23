package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

val <S, F> List<Either<S, F>>.collect: ListSequencer<S, F>
    get() = ListSequencer(this)

class ListSequencer<S, F> {

    val collector: IterableCollector<S, F>
    constructor(list: List<Either<S, F>>) {
        this.collector = IterableCollector(list)
    }

    fun applicative(): Either<List<S>, List<F>> = collector.applicative().mapEither(
        onSuccess = { it.toList() },
        onFailure = { it.toList() }
    )


    fun monadic(): Either<List<S>, F> = collector.monadic().map(
        onSuccess = { it.toList() }
    )

}
package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

val <S, F> Iterator<Either<S, F>>.collect: IteratorCollector<S, F>
    get() = IteratorCollector(this)

class IteratorCollector<S, F>(iterator: Iterator<Either<S, F>>) {

    private val collector: IterableCollector<S, F> = IterableCollector(iterator.asSequence().asIterable())


    fun applicative(): Either<Iterator<S>, Iterator<F>> = collector.applicative().mapEither(
        onSuccess = { it.iterator() },
        onFailure = { it.iterator() }
    )


    fun monadic(): Either<Iterator<S>, F> = collector.monadic().map(
        onSuccess = { it.iterator() }
    )

}
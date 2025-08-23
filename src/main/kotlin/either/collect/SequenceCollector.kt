package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.map.map

val <S, F> Sequence<Either<S, F>>.collect: SequenceCollector<S, F>
    get() = SequenceCollector(this)

class SequenceCollector<S, F>(sequence: Sequence<Either<S, F>>) {

    private val collector: IterableCollector<S, F> = IterableCollector(sequence.asIterable())

    fun applicative(): Either<Sequence<S>, Sequence<F>> {
        return collector.applicative().either(
            onSuccess = { Either.success(it.asSequence()) },
            onFailure = { Either.failure(it.asSequence()) }
        )
    }

    fun monadic(): Either<Sequence<S>, F> {
        return collector.monadic().map { it.asSequence() }
    }
}
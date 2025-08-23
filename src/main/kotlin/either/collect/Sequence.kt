package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.map.map
import kotlin.sequences.asIterable

val <S, F> Sequence<Either<S, F>>.collect: SequenceSequencer<S, F>
    get() = SequenceSequencer(this)

class SequenceSequencer<S, F> {

    val collector: IterableCollector<S, F>
    constructor(sequence: Sequence<Either<S, F>>) {
        this.collector = IterableCollector(sequence.asIterable())
    }

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
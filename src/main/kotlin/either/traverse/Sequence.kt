package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.map.map

val <V> Sequence<V>.traverse: SequenceTraverser<V>
    get() = SequenceTraverser(this)

class SequenceTraverser<V> {
    val traverser: IterableTraverser<V>
    constructor(sequence: Sequence<V>) {
        this.traverser = IterableTraverser(sequence.asIterable())
    }


    fun <S, F> applicative(mapping: (V) -> Either<S, F>): Either<Sequence<S>, Sequence<F>> {
        return traverser.applicative(mapping).either(
            onSuccess = { Either.success(it.asSequence()) },
            onFailure = { Either.failure(it.asSequence()) }
        )
    }

    fun <S, F> monadic(mapping: (V) -> Either<S, F>): Either<Sequence<S>, F> {
        return traverser.monadic(mapping).map { it.asSequence() }
    }
}
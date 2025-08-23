package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.map.map

val <V> Iterator<V>.traverse: IteratorTraverser<V>
    get() = IteratorTraverser(this)

class IteratorTraverser<V>(iterator: Iterator<V>) {
    private val traverser: IterableTraverser<V> = IterableTraverser(iterator.asSequence().asIterable())


    fun <S, F> applicative(mapping: (V) -> Either<S, F>): Either<Iterator<S>, Iterator<F>> {
        return traverser.applicative(mapping).either(
            onSuccess = { Either.success(it.iterator()) },
            onFailure = { Either.failure(it.iterator()) }
        )
    }

    fun <S, F> monadic(mapping: (V) -> Either<S, F>): Either<Iterator<S>, F> {
        return traverser.monadic(mapping).map { it.iterator() }
    }
}
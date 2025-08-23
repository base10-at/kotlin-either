package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

val <V> List<V>.traverse: ListTraverser<V> get() = ListTraverser(this)

class ListTraverser<V> {

    constructor(list: List<V>) {
        this.traverser = IterableTraverser(list)
    }

    val traverser: IterableTraverser<V>
    fun <S, F> applicative(mapping: (V) -> Either<S, F>): Either<List<S>, List<F>> {
        return traverser.applicative(mapping).mapEither(
            onSuccess = { it.toList() },
            onFailure = { it.toList() }
        )
    }

    fun <S, F> monadic(mapping: (V) -> Either<S, F>): Either<List<S>, F> {
        return traverser.monadic(mapping).map { it.toList() }
    }
}
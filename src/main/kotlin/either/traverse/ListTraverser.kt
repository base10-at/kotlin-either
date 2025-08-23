package at.base10.either.traverse

import at.base10.either.Either

val <V> List<V>.traverse: ListTraverser<V> get() = ListTraverser(this)

class ListTraverser<V>(list: List<V>) {

    private val traverser: IterableTraverser<V> = IterableTraverser(list)

    @Suppress("UNCHECKED_CAST")
    fun <S, F> applicative(mapping: (V) -> Either<S, F>): Either<List<S>, List<F>> =
        traverser.applicative(mapping) as Either<List<S>, List<F>>

    @Suppress("UNCHECKED_CAST")
    fun <S, F> monadic(mapping: (V) -> Either<S, F>): Either<List<S>, F> =
        traverser.monadic(mapping) as Either<List<S>, F>
}
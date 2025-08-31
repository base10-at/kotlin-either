package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapEither

internal fun <E> asSequence(e: Iterable<E>): Sequence<E> = e.asSequence()
internal fun <E> iterator(e: Iterable<E>): Iterator<E> = e.iterator()
internal fun <V> Iterator<V>.asIterable() = this
    .asSequence()
    .asIterable()

internal fun <V, S, F> Iterable<V>.mapAsIterable(transformer: (V) -> Either<S, F>) = this
    .asSequence()
    .map(transformer)
    .asIterable()


internal fun <S, F> Either<Iterable<S>, Iterable<F>>.mapEitherToIterator() = this
    .mapEither(::iterator, ::iterator)

internal fun <S, F> Pair<Iterable<S>, Iterable<F>>.mapPairToIterator() = Pair(
    this.first.iterator(), this.second.iterator()
)

internal fun <S, F> Either<Iterable<S>, F>.mapToIterator() = this
    .map(::iterator)

@Suppress("UNCHECKED_CAST")
internal fun <S, F> Either<Iterable<S>, F>.mapToList() = this as Either<List<S>, F>

@Suppress("UNCHECKED_CAST")
internal fun <S, F> Either<Iterable<S>, Iterable<F>>.mapEitherToList() = this as Either<List<S>, List<F>>

@Suppress("UNCHECKED_CAST")
internal fun <S, F> Pair<Iterable<S>, Iterable<F>>.mapPairToList() = Pair(
    this.first as List<S>, this.second as List<F>
)

internal fun <S, F> Either<Iterable<S>, Iterable<F>>.mapEitherToSequence() = this
    .mapEither(::asSequence, ::asSequence)

internal fun <S, F> Pair<Iterable<S>, Iterable<F>>.mapPairToSequence() = Pair(
    this.first.asSequence(), this.second.asSequence()
)


internal fun <S, F> Either<Iterable<S>, F>.mapToSequence() = this
    .map(::asSequence)


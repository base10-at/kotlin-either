package at.base10.either.traverse

import at.base10.either.Either

val <V> Iterable<V>.traverse: IterableTraverser<V> get() = IterableTraverser(this)

class IterableTraverser<V>(private val iterable: Iterable<V>) {

    fun <S, F> applicative(mapping: (V) -> Either<S, F>): Either<Iterable<S>, Iterable<F>> {
        val successList = mutableListOf<S>()
        val failureList = mutableListOf<F>()

        for (element in iterable) {
            when (val mapped = mapping(element)) {
                is Either.Failure -> failureList.add(mapped.value)
                else -> successList.add((mapped as Either.Success<S, *>).value)
            }
        }
        return when {
            failureList.isNotEmpty() -> Either.failure(failureList.toList())
            else -> Either.success(successList.toList())

        }
    }

    fun <S, F> monadic(mapping: (V) -> Either<S, F>): Either<Iterable<S>, F> {
        val successList = mutableListOf<S>()
        for (element in iterable) {

            when (val mapped = mapping(element)) {
                is Either.Failure -> return Either.failure(mapped.value)
                else -> successList.add((mapped as Either.Success<S, *>).value)
            }
        }
        return Either.success(successList)
    }


}
package at.base10.either.traverse

import at.base10.either.Either

val <V> Iterable<V>.traverse: IterableTraverser<V> get() = IterableTraverser(this)

class IterableTraverser<V>(val iterable: Iterable<V>) {

    fun <S, F> applicative(mapping: (V) -> Either<S, F>): Either<Iterable<S>, Iterable<F>> {
        val successList = mutableListOf<S>()
        val failureList = mutableListOf<F>()

        for (element in iterable) {
            val mapped = mapping(element)
            mapped.either(
                onSuccess = { successList.add(it) },
                onFailure = { failureList.add(it) }
            )
        }
        return if (failureList.isNotEmpty()) {
            Either.failure(failureList.toList())
        } else {
            Either.success(successList.toList())
        }
    }

    fun <S, F> monadic(mapping: (V) -> Either<S, F>): Either<Iterable<S>, F> {
        val successList = mutableListOf<S>()
        for (element in iterable) {
            when (val mapped = mapping(element)) {
                is Either.Failure -> return Either.failure(mapped.value)
                is Either.Success -> successList.add(mapped.value)
            }
        }
        return Either.success(successList)
    }


}
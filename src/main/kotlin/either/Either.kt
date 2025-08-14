package at.base10.either

import at.base10.either.Either.Companion.failure
import at.base10.either.Either.Companion.success


sealed class Either<out S, out F> {

    data class Failure<out S, out F>(val value: F) : Either<S, F>()
    data class Success<out S, out F>(val value: S) : Either<S, F>()

    companion object {
        fun <S> success(value: S): Either<S, Nothing> = Success(value = value)
        fun <F> failure(value: F): Either<Nothing, F> = Failure(value = value)
    }

    fun <R> either(
        onSuccess: (S) -> R,
        onFailure: (F) -> R
    ): R = when (this) {
        is Failure -> onFailure(value)
        is Success -> onSuccess(value)
    }
}

fun <S, F, S1, F1> Either<S, F>.mapEither(
    onSuccess: (S) -> S1,
    onFailure: (F) -> F1,
): Either<S1, F1> = bindEither(
    { success(onSuccess(it)) },
    { failure(onFailure(it)) }
)

fun <S, F, S1> Either<S, F>.map(
    onSuccess: (S) -> S1,
): Either<S1, F> = mapEither(onSuccess = onSuccess, onFailure = { it })

fun <S, F, F1> Either<S, F>.mapFailure(
    onFailure: (F) -> F1,
): Either<S, F1> = mapEither(onSuccess = { it }, onFailure = onFailure)


fun <S, F, S1, F1> Either<S, F>.bindEither(
    onSuccess: (S) -> Either<S1, F1>,
    onFailure: (F) -> Either<S1, F1>
): Either<S1, F1> = either(onSuccess = onSuccess, onFailure = onFailure)


fun <S, F, S1> Either<S, F>.bind(
    onSuccess: (S) -> Either<S1, F>,
): Either<S1, F> = either(
    onSuccess = onSuccess,
    onFailure = { failure(it) },
)

fun <S, F, F1> Either<S, F>.bindFailure(
    onFailure: (F) -> Either<S, F1>,
): Either<S, F1> = either(
    onSuccess = { success(it) },
    onFailure = onFailure
)

fun <S, F> Either<S, F>.toNullable(): S? = either(
    onSuccess = { it },
    onFailure = { null }
)

fun <S, F> Either<S, F>.failureOrNull(): F? = either(
    onSuccess = { null },
    onFailure = { it }
)

fun <S, F> Either<S, F>.swap(): Either<F, S> = either(
    onSuccess = { failure(it) },
    onFailure = { success(it) }
)

fun <S, F> Either<S, F>.orElse(
    onFailure: (F) -> S,
): S = either(
    onSuccess = { it },
    onFailure = onFailure
)

fun <S, F> Either<S, F>.recover(
    onFailure: (F) -> S,
): Either<S, Nothing> = success(orElse(onFailure))


val <S> Either<S, Nothing>.success: S
    get() = either(
        onSuccess = { it },
        onFailure = { it }
    )

val <F> Either<Nothing, F>.failure: F
    get() = either(
        onSuccess = { it },
        onFailure = { it }
    )

val <S, F> Either<S, F>.count: Int
    get() = either(
        onSuccess = { 1 },
        onFailure = { 0 }
    )

class Sequencer<S, F>(val iterator: Iterator<Either<S, F>>) {
    fun applicative(): Either<List<S>, List<F>> {
        val successList = mutableListOf<S>()
        val failureList = mutableListOf<F>()
        for (element in iterator) {
            element.either(
                onSuccess = { successList.add(it) },
                onFailure = { failureList.add(it) }
            )
        }
        return if (failureList.isNotEmpty()) {
            failure(failureList.toList())
        } else {
            success(successList.toList())
        }
    }

    fun monadic(): Either<List<S>, F> {
        val successList = mutableListOf<S>()
        for (element in iterator) {
            when (element) {
                is Either.Failure -> return failure(element.value)
                is Either.Success -> successList.add(element.value)
            }
        }
        return success(successList.toList())
    }
}

class Traverser<V>(val iterator: Iterator<V>) {
    fun <S, F> applicative(mapping: (V) -> Either<S, F>): Either<Iterable<S>, Iterable<F>> {
        return iterator.asSequence().map(mapping).iterator().sequence.applicative()
    }
    fun <S, F> monadic(mapping: (V) -> Either<S, F>): Either<List<S>, F> {
        return iterator.asSequence().map(mapping).iterator().sequence.monadic()
    }
}

val <S, F> Iterator<Either<S, F>>.sequence: Sequencer<S, F> get() = Sequencer(this)
val <V> Iterator<V>.traverse: Traverser<V> get() = Traverser(this)

val <S, F> Iterable<Either<S, F>>.sequence: Sequencer<S, F> get() = Sequencer(this.iterator())
val <V> Iterable<V>.traverse: Traverser<V> get() = Traverser(this.iterator())

val <S, F> Sequence<Either<S, F>>.sequence: Sequencer<S, F> get() = Sequencer(this.iterator())
val <V> Sequence<V>.traverse: Traverser<V> get() = Traverser(this.iterator())




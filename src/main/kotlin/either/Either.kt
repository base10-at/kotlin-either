package at.base10.either


sealed class Either<out S, out F> {

    internal data class Failure<out S, out F>(internal val value: F) : Either<S, F>()
    internal data class Success<out S, out F>(internal val value: S) : Either<S, F>()

    companion object {
        fun <S> success(value: S): Either<S, Nothing> = Success(value = value)
        fun <F> failure(value: F): Either<Nothing, F> = Failure(value = value)
    }

    fun <R> either(
        onSuccess: (S) -> R,
        onFailure: (F) -> R
    ): R = when (this) {
        is Success -> onSuccess(value)
        else -> onFailure((this as Failure<*, F>).value)
    }
}




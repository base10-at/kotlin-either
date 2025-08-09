package at.base10.either


sealed interface Either<out S, out F> {

    data class Failure<out S, out F>(val failure: F) : Either<S, F>
    data class Success<out S, out F>(val success: S) : Either<S, F>

    companion object {
        fun <S> success(success: S): Either<S, Nothing> = Success(success = success)
        fun <F> failure(failure: F): Either<Nothing, F> = Failure(failure = failure)
    }

    fun <S, F, R> Either<S, F>.either(
        onSuccess: (S) -> R,
        onFailure: (F) -> R
    ): R = when (this) {
        is Failure -> onFailure(failure)
        is Success -> onSuccess(success)
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


}


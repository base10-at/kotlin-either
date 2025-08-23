package at.base10.either

val <S, F> Either<S, F>.isSuccess: Boolean
    get() = either(
        onSuccess = { true },
        onFailure = { false }
    )
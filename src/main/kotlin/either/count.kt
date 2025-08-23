package at.base10.either

val <S, F> Either<S, F>.count: Int
    get() = either(
        onSuccess = { 1 },
        onFailure = { 0 },
    )
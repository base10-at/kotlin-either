package at.base10.either

val <S> Either<S, Nothing>.success: S
    get() = either(
        onSuccess = { it },
        onFailure = { it }
    )
package at.base10.either.value

import at.base10.either.Either

val <S> Either<S, Nothing>.success: S
    get() = either(
        onSuccess = { it },
        onFailure = { it }
    )
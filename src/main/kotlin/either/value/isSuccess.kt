package at.base10.either.value

import at.base10.either.Either

val <S, F> Either<S, F>.isSuccess: Boolean
    get() = either(
        onSuccess = { true },
        onFailure = { false }
    )
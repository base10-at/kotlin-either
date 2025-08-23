package at.base10.either.value

import at.base10.either.Either

val <F> Either<Nothing, F>.failure: F
    get() = either(
        onSuccess = { it },
        onFailure = { it },
    )
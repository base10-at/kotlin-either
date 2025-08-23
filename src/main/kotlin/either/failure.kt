package at.base10.either

val <F> Either<Nothing, F>.failure: F
    get() = either(
        onSuccess = { it },
        onFailure = { it }
    )
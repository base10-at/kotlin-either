package at.base10.either

fun <S, F> Either<S, F>.orNull(): S? = either(
    onSuccess = { it },
    onFailure = { null }
)
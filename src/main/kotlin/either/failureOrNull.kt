package at.base10.either

fun <S, F> Either<S, F>.failureOrNull(): F? = either(
    onSuccess = { null },
    onFailure = { it }
)
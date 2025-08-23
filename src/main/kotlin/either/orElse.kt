package at.base10.either

fun <S, F> Either<S, F>.orElse(
    onFailure: (F) -> S,
): S = either(
    onSuccess = { it },
    onFailure = onFailure
)
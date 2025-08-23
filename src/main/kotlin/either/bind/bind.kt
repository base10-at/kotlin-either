package at.base10.either.bind

import at.base10.either.Either

fun <S, F, S1> Either<S, F>.bind(
    onSuccess: (S) -> Either<S1, F>,
): Either<S1, F> = either(
    onSuccess = onSuccess,
    onFailure = { Either.failure(it) },
)
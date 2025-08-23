package at.base10.either.bind

import at.base10.either.Either

fun <S, F, S1, F1> Either<S, F>.bindEither(
    onSuccess: (S) -> Either<S1, F1>,
    onFailure: (F) -> Either<S1, F1>
): Either<S1, F1> = either(
    onSuccess = onSuccess,
    onFailure = onFailure
)
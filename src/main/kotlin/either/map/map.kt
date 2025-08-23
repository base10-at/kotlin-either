package at.base10.either.map

import at.base10.either.Either

fun <S, F, S1> Either<S, F>.map(
    onSuccess: (S) -> S1,
): Either<S1, F> = mapEither(onSuccess = onSuccess, onFailure ={ it })
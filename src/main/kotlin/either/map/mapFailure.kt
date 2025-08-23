package at.base10.either.map

import at.base10.either.Either

fun <S, F, F1> Either<S, F>.mapFailure(
    onFailure: (F) -> F1,
): Either<S, F1> = mapEither(onSuccess ={ it }, onFailure = onFailure)
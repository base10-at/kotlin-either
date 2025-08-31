package at.base10.either.transform

import at.base10.either.Either
import at.base10.either.success
import at.base10.either.value.orElse


fun <S, F> Either<S, F>.recover(
    onFailure: (F) -> S,
): Either<S, Nothing> = success(orElse(onFailure))

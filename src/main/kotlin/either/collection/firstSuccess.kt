package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.value.isSuccess
import at.base10.either.value.orNull

fun <S, F> List<Either<S, F>>.firstSuccess(): S? {
    return find { it.isSuccess }?.orNull()
}


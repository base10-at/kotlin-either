package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.value.failureOrNull
import at.base10.either.value.isFailure

fun <S, F> List<Either<S, F>>.firstFailure(): F? {
    return find { it.isFailure }?.failureOrNull()
}
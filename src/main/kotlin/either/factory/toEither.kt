package at.base10.either.factory

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success

fun <T> T?.toEither(): Either<T, Nothing?> {
    return when (this) {
        null -> failure(null)
        else -> success(this)
    }
}

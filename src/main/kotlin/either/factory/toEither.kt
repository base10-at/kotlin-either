package at.base10.either.factory

import at.base10.either.Either
import at.base10.either.Either.Failure
import at.base10.either.Either.Success
import at.base10.either.failure
import at.base10.either.success

/**
 * Converts this nullable value into an [Either].
 *
 * - If this value is non-null, returns a [Success] containing the value.
 * - If this value is null, returns a [Failure] containing `null`.
 *
 * This can be useful when you want to treat nullable values as an `Either` type
 * for further functional composition.
 *
 * @receiver a nullable value of type [T].
 * @return a [Success] containing the value, which may be null.
 */
fun <T> T?.toEither(): Either<T, Nothing?> {
    return when (this) {
        null -> failure(null)
        else -> success(this)
    }
}

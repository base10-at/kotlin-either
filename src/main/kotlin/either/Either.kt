package at.base10.either

import at.base10.either.Either.Failure
import at.base10.either.Either.Success

/**
 * Represents a value of one of two possible types: a success value [S] or a failure value [F].
 *
 * This is a common functional programming pattern that makes it explicit when a computation
 * can either succeed with a result of type [S] or fail with a value of type [F].
 *
 * @param S the type of the success value.
 * @param F the type of the failure value.
 */
sealed class Either<out S, out F> {
    /**
     * Represents a failure outcome of type [F].
     *
     * @param value the failure value.
     */
    data class Failure<out S, out F>(internal val value: F) : Either<S, F>()

    /**
     * Represents a success outcome of type [S].
     *
     * @param value the success value.
     */
    data class Success<out S, out F>(internal val value: S) : Either<S, F>(

    ) {

        override fun hashCode(): Int {
            return (value.hashCode()) + Int.MAX_VALUE
        }
    }

    /**
     * Applies one of the given functions depending on whether this [Either]
     * is a [Success] or a [Failure].
     *
     * - If this is a [Success], calls [onSuccess] with the success value.
     * - If this is a [Failure], calls [onFailure] with the failure value.
     *
     * @param onSuccess function to apply if this is a [Success].
     * @param onFailure function to apply if this is a [Failure].
     * @return the result of applying the corresponding function.
     */
    fun <R> either(
        onSuccess: (S) -> R,
        onFailure: (F) -> R
    ): R = when (this) {
        is Success -> onSuccess(value)
        else -> onFailure((this as Failure<*, F>).value)
    }
}

/**
 * Creates a [Success] instance wrapping the given [value].
 *
 * This is a convenience function for constructing an [Either] that
 * represents a successful outcome.
 *
 * Example:
 * ```
 * val result: Either<Int, Nothing> = success(42)
 * ```
 *
 * @param value the success value.
 * @return an [Either] instance containing the success value.
 */
fun <S> success(value: S): Either<S, Nothing> = Success(value = value)

/**
 * Creates a [Failure] instance wrapping the given [value].
 *
 * This is a convenience function for constructing an [Either] that
 * represents a failure outcome.
 *
 * Example:
 * ```
 * val result: Either<Nothing, String> = failure("Something went wrong")
 * ```
 *
 * @param value the failure value.
 * @return an [Either] instance containing the failure value.
 */
fun <F> failure(value: F): Either<Nothing, F> = Failure(value = value)
package at.base10.either

import at.base10.either.bind.bindEither
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class BindEitherFn {

    @Test
    fun `should bindFailure to Success value when success`() {
        val subject: Either<String, Int> = Either.failure(1)
        val actual = subject.bindEither(
            onSuccess = { Either.success("SUCCESS") },
            onFailure = { Either.success("FAILURE") }
        )
        expectThat(actual) isEqualTo Either.success("FAILURE")
    }

    @Test
    fun `should not bindFailure to Success value when failure`() {
        val subject: Either<String, Int> = Either.success("success")
        val actual = subject.bindEither(
            onSuccess = { Either.success("SUCCESS") },
            onFailure = { Either.success("FAILURE") }
        )
        expectThat(actual) isEqualTo Either.success("SUCCESS")
    }


    @Test
    fun `should bindFailure to Failure value when success`() {
        val subject: Either<String, Int> = Either.failure(1)
        val actual = subject.bindEither(
            onSuccess = { Either.failure("SUCCESS") },
            onFailure = { Either.failure("FAILURE") }
        )
        expectThat(actual) isEqualTo Either.failure("FAILURE")
    }

    @Test
    fun `should not bindFailure to Failure value when failure`() {
        val subject: Either<String, Int> = Either.success("success")
        val actual = subject.bindEither(
            onSuccess = { Either.failure("SUCCESS") },
            onFailure = { Either.failure("FAILURE") }
        )
        expectThat(actual) isEqualTo Either.failure("SUCCESS")
    }

}
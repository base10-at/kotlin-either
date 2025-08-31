package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class BindEitherFn {

    @Test
    fun `should bindFailure to Success value when success`() {
        val subject: Either<String, Int> = failure(1)
        val actual = subject.flatMapEither(
            onSuccess = { success("SUCCESS") },
            onFailure = { success("FAILURE") }
        )
        expectThat(actual) isEqualTo success("FAILURE")
    }

    @Test
    fun `should not bindFailure to Success value when failure`() {
        val subject: Either<String, Int> = success("success")
        val actual = subject.flatMapEither(
            onSuccess = { success("SUCCESS") },
            onFailure = { success("FAILURE") }
        )
        expectThat(actual) isEqualTo success("SUCCESS")
    }


    @Test
    fun `should bindFailure to Failure value when success`() {
        val subject: Either<String, Int> = failure(1)
        val actual = subject.flatMapEither(
            onSuccess = { failure("SUCCESS") },
            onFailure = { failure("FAILURE") }
        )
        expectThat(actual) isEqualTo failure("FAILURE")
    }

    @Test
    fun `should not bindFailure to Failure value when failure`() {
        val subject: Either<String, Int> = success("success")
        val actual = subject.flatMapEither(
            onSuccess = { failure("SUCCESS") },
            onFailure = { failure("FAILURE") }
        )
        expectThat(actual) isEqualTo failure("SUCCESS")
    }

}
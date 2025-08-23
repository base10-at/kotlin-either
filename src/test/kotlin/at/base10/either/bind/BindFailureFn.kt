package at.base10.either.bind

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class BindFailureFn {


    @Test
    fun `should bindFailure to Success value when success`() {
        val subject: Either<String, Int> = Either.failure(1)
        val actual = subject.bindFailure { Either.success("BIND") }
        expectThat(actual) isEqualTo Either.success("BIND")
    }

    @Test
    fun `should not bindFailure to Success value when failure`() {
        val subject: Either<String, Int> = Either.success("success")
        val actual = subject.bindFailure { Either.success("BIND") }
        expectThat(actual) isEqualTo Either.success("success")
    }

    @Test
    fun `should bindFailure to Failure value when success`() {
        val subject: Either<String, Int> = Either.failure(1)
        val actual = subject.bindFailure { Either.failure(it + 1) }
        expectThat(actual) isEqualTo Either.failure(2)
    }

    @Test
    fun `should not bindFailure to Failure value when failure`() {
        val subject: Either<String, Int> = Either.success("success")
        val actual = subject.bindFailure { Either.failure("BIND") }
        expectThat(actual) isEqualTo Either.success("success")
    }
}
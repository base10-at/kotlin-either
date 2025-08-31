package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class BindFailureFn {


    @Test
    fun `should bindFailure to Success value when success`() {
        val subject: Either<String, Int> = failure(1)
        val actual = subject.flatMapFailure { success("BIND") }
        expectThat(actual) isEqualTo success("BIND")
    }

    @Test
    fun `should not bindFailure to Success value when failure`() {
        val subject: Either<String, Int> = success("success")
        val actual = subject.flatMapFailure { success("BIND") }
        expectThat(actual) isEqualTo success("success")
    }

    @Test
    fun `should bindFailure to Failure value when success`() {
        val subject: Either<String, Int> = failure(1)
        val actual = subject.flatMapFailure { failure(it + 1) }
        expectThat(actual) isEqualTo failure(2)
    }

    @Test
    fun `should not bindFailure to Failure value when failure`() {
        val subject: Either<String, Int> = success("success")
        val actual = subject.flatMapFailure { failure("BIND") }
        expectThat(actual) isEqualTo success("success")
    }
}
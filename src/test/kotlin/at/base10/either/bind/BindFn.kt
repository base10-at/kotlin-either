package at.base10.either.bind

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class BindFn {

    @Test
    fun `should bind to Success value when success`() {
        val subject = Either.success(1)
        val actual = subject.bind { Either.success(it + 1) }
        expectThat(actual) isEqualTo Either.success(2)
    }

    @Test
    fun `should not bind to Success value when failure`() {
        val subject: Either<Int, String> = Either.failure("failure")
        val actual = subject.bind { Either.success(it + 1) }
        expectThat(actual) isEqualTo Either.failure("failure")
    }

    @Test
    fun `should bind to Failure value when success`() {
        val subject = Either.success(1)
        val actual = subject.bind { Either.failure(it + 1) }
        expectThat(actual) isEqualTo Either.failure(2)
    }

    @Test
    fun `should not bind to Failure value when failure`() {
        val subject: Either<Int, String> = Either.failure("failure")
        val actual = subject.bind { Either.failure("BIND") }
        expectThat(actual) isEqualTo Either.failure("failure")
    }
}
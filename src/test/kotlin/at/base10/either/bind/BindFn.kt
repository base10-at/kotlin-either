package at.base10.either.bind

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class BindFn {

    @Test
    fun `should bind to Success value when success`() {
        val subject = success(1)
        val actual = subject.flatMap { success(it + 1) }
        expectThat(actual) isEqualTo success(2)
    }

    @Test
    fun `should not bind to Success value when failure`() {
        val subject: Either<Int, String> = failure("failure")
        val actual = subject.flatMap { success(it + 1) }
        expectThat(actual) isEqualTo failure("failure")
    }

    @Test
    fun `should bind to Failure value when success`() {
        val subject = success(1)
        val actual = subject.flatMap { failure(it + 1) }
        expectThat(actual) isEqualTo failure(2)
    }

    @Test
    fun `should not bind to Failure value when failure`() {
        val subject: Either<Int, String> = failure("failure")
        val actual = subject.flatMap { failure("BIND") }
        expectThat(actual) isEqualTo failure("failure")
    }
}
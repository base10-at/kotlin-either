package at.base10.either.value

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class FailureOrNullFn {

    @Test
    fun `should get value when success`() {
        val actual = Either.failure(1).failureOrNull()
        expectThat(actual) isEqualTo 1
    }

    @Test
    fun `should get null when failure`() {
        val actual = Either.success(1).failureOrNull()
        expectThat(actual) isEqualTo null
    }
}
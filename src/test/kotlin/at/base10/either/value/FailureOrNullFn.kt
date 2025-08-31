package at.base10.either.value

import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class FailureOrNullFn {

    @Test
    fun `should get value when success`() {
        val actual = failure(1).failureOrNull()
        expectThat(actual) isEqualTo 1
    }

    @Test
    fun `should get null when failure`() {
        val actual = success(1).failureOrNull()
        expectThat(actual) isEqualTo null
    }
}
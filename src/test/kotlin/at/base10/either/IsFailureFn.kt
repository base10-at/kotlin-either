package at.base10.either

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class IsFailureFn {
    @Test
    fun `should be true when failure`() {
        val actual = Either.failure(1).isFailure
        expectThat(actual) isEqualTo true
    }

    @Test
    fun `should be false when success`() {
        val actual = Either.success(1).isFailure
        expectThat(actual) isEqualTo false
    }
}
package at.base10.either.value

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class IsSuccessFn {
    @Test
    fun `should be true when success`() {
        val actual = Either.success(1).isSuccess
        expectThat(actual) isEqualTo true
    }

    @Test
    fun `should be false when failure`() {
        val actual = Either.failure(1).isSuccess
        expectThat(actual) isEqualTo false
    }
}
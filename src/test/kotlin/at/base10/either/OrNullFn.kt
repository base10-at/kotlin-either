package at.base10.either

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class OrNullFn {
    @Test
    fun `should get value when success`() {
        val actual = Either.success(1).orNull()
        expectThat(actual) isEqualTo 1
    }

    @Test
    fun `should get null when failure`() {
        val actual = Either.failure(1).orNull()
        expectThat(actual) isEqualTo null
    }
}
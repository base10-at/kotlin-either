package at.base10.either.value

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class OrElseFn {
    @Test
    fun `should get value when success`() {
        val actual = Either.success(1).orElse { _ -> 2 }
        expectThat(actual) isEqualTo 1
    }

    @Test
    fun `should map value when failure`() {
        val actual = Either.failure(1).orElse { it * 2 }
        expectThat(actual) isEqualTo 2
    }
}
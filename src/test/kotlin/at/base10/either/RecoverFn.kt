package at.base10.either

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class RecoverFn {
    @Test
    fun `should recover when success`() {
        val actual = Either.success(1).recover { _ -> 2 }
        expectThat(actual) isEqualTo Either.success(1)
    }

    @Test
    fun `should recover when failure`() {
        val actual = Either.failure(21).recover { it * 2 }
        expectThat(actual) isEqualTo Either.success(42)
    }

}
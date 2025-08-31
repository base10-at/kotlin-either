package at.base10.either.transform


import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class RecoverFn {
    @Test
    fun `should recover when success`() {
        val actual = success(1).recover { _ -> 2 }
        expectThat(actual) isEqualTo success(1)
    }

    @Test
    fun `should recover when failure`() {
        val actual = failure(21).recover { it * 2 }
        expectThat(actual) isEqualTo success(42)
    }

}
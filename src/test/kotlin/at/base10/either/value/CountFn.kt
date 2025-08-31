package at.base10.either.value

import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CountFn {
    @Test
    fun `should count when success`() {
        val actual = success(1).count
        expectThat(actual) isEqualTo 1
    }

    @Test
    fun `should count when failure`() {
        val actual = failure(1).count
        expectThat(actual) isEqualTo 0
    }
}
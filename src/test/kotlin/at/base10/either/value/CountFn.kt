package at.base10.either.value

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CountFn {
    @Test
    fun `should count when success`() {
        val actual = Either.success(1).count
        expectThat(actual) isEqualTo 1
    }

    @Test
    fun `should count when failure`() {
        val actual = Either.failure(1).count
        expectThat(actual) isEqualTo 0
    }
}
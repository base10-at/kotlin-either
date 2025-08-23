package at.base10.either

import at.base10.either.map.map
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class MapFn {

    @Test
    fun `should map value when success`() {
        val subject = Either.success(1)
        val actual = subject.map { it + 1 }
        expectThat(actual) isEqualTo Either.success(2)
    }

    @Test
    fun `should not map value when failure`() {
        val subject: Either<Int, String> = Either.failure("failure")
        val actual = subject.map { it + 1 }
        expectThat(actual) isEqualTo Either.failure("failure")
    }
}
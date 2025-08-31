package at.base10.either.map

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class MapFn {

    @Test
    fun `should map value when success`() {
        val subject = success(1)
        val actual = subject.map { it + 1 }
        expectThat(actual) isEqualTo success(2)
    }

    @Test
    fun `should not map value when failure`() {
        val subject: Either<Int, String> = failure("failure")
        val actual = subject.map { it + 1 }
        expectThat(actual) isEqualTo failure("failure")
    }
}
package at.base10.either.value

import at.base10.either.failure
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class GetFailureFn {
    @Test
    fun `should get failure when failure`() {
        val actual = failure(1).failure
        expectThat(actual) isEqualTo 1
    }
}
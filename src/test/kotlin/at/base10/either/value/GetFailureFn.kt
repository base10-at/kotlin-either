package at.base10.either.value

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class GetFailureFn {
    @Test
    fun `should get failure when failure`() {
        val actual = Either.failure(1).failure
        expectThat(actual) isEqualTo 1
    }
}
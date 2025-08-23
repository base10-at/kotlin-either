package at.base10.either.value

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class GetSuccessFn {

    @Test
    fun `should get success when success`() {
        val actual = Either.success(1).success
        expectThat(actual) isEqualTo 1
    }
}
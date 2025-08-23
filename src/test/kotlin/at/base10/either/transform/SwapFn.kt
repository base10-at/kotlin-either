package at.base10.either.transform

import at.base10.either.Either
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class SwapFn {

    @Test
    fun `should swap values when success`() {
        expectThat(Either.success(1).swap()) isEqualTo Either.failure(1)
        expectThat(Either.failure(2).swap()) isEqualTo Either.success(2)
    }
}
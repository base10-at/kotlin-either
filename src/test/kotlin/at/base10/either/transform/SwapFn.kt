package at.base10.either.transform

import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SwapFn {

    @Test
    fun `should swap values when success`() {
        expectThat(success(1).swap()) isEqualTo failure(1)
        expectThat(failure(2).swap()) isEqualTo success(2)
    }
}
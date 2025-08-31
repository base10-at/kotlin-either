package at.base10.either.map

import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class MapEitherFn {

    @Test
    fun `should map value when success`() {
        val subject = failure(1)
        val actual = subject.mapEither(
            onSuccess = { 0 },
            onFailure = { it + 1 },
        )
        expectThat(actual) isEqualTo failure(2)
    }

    @Test
    fun `should not map value when success`() {
        val subject = success(1)
        val actual = subject.mapEither(
            onSuccess = { it + 1 },
            onFailure = { 0 },
        )
        expectThat(actual) isEqualTo success(2)
    }
}
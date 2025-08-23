package at.base10.either

import at.base10.either.map.mapEither
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class MapEitherFn {

    @Test
    fun `should map value when success`() {
        val subject = Either.failure(1)
        val actual = subject.mapEither(
            onSuccess = { 0 },
            onFailure = { it + 1 },
        )
        expectThat(actual) isEqualTo Either.failure(2)
    }

    @Test
    fun `should not map value when success`() {
        val subject = Either.success(1)
        val actual = subject.mapEither(
            onSuccess = { it + 1 },
            onFailure = { 0 },
        )
        expectThat(actual) isEqualTo Either.success(2)
    }
}
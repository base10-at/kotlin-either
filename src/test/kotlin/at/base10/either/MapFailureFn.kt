package at.base10.either

import at.base10.either.map.mapFailure
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class MapFailureFn {

    @Test
    fun `should map value when failure`() {
        val subject = Either.failure(1)
        val actual = subject.mapFailure { it + 1 }
        expectThat(actual) isEqualTo Either.failure(2)
    }

    @Test
    fun `should not map value when success`() {
        val subject: Either<String, Int> = Either.success("success")
        val actual = subject.mapFailure { it + 1 }
        expectThat(actual) isEqualTo Either.success("success")
    }
}
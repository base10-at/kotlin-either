package at.base10.either

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo


class EitherTest {

    @Test
    fun `should be equal`() {
        expectThat(Either.success(1)) isEqualTo Either.success(1)
        expectThat(Either.failure(2)) isEqualTo Either.failure(2)
    }

    @Test
    fun `should not be equal`() {
        expectThat(Either.success(1)) isNotEqualTo Either.success(2)
        expectThat(Either.failure(2)) isNotEqualTo Either.failure(1)
        expectThat(Either.success(1) as Either<*, *>) isNotEqualTo Either.failure(1)
        expectThat(Either.failure(2) as Either<*, *>) isNotEqualTo Either.success(2)
    }
}
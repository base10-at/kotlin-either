package at.base10.either

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo


class EitherTest {

    @Test
    fun `should be equal`() {
        expectThat(success(1)) isEqualTo success(1)
        expectThat(failure(2)) isEqualTo failure(2)
    }

    @Test
    fun `should not be equal`() {
        expectThat(success(1)) isNotEqualTo success(2)
        expectThat(failure(2)) isNotEqualTo failure(1)
        expectThat(success(1).hashCode()) isNotEqualTo failure(1).hashCode()
        expectThat(failure(null).hashCode()) isEqualTo 0
        expectThat(success(null).hashCode()) isNotEqualTo failure(null).hashCode()
        expectThat(success(1) as Either<*, *>) isNotEqualTo failure(1)
        expectThat(success(1) as Either<*, *>) isNotEqualTo success(2)
        expectThat(failure(2) as Either<*, *>) isNotEqualTo success(2)
        expectThat(failure(1) as Either<*, *>) isNotEqualTo failure(2)
    }
}
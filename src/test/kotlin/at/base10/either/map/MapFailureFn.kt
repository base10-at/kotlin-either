package at.base10.either.map


import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class MapFailureFn {

    @Test
    fun `should map value when failure`() {
        val subject = failure(1)
        val actual = subject.mapFailure { it + 1 }
        expectThat(actual) isEqualTo failure(2)
    }

    @Test
    fun `should not map value when success`() {
        val subject: Either<String, Int> = success("success")
        val actual = subject.mapFailure { it + 1 }
        expectThat(actual) isEqualTo success("success")
    }
}
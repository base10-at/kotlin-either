package at.base10.either.factory

import at.base10.either.Either
import at.base10.either.value.failure
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull


class ToEitherFn {

    @Test
    fun `toEither should return success value`() {
        val actual: Either<String, Nothing?> = "success".toEither()
        expectThat(actual) isEqualTo Either.Success("success")
    }

    @Test
    fun `toEither should equal failure value`() {
        val value: String? = null
        val actual = value.toEither()
        val expected = Either.Failure<Nothing, Nothing?>(null)
        expectThat(actual) isEqualTo expected
    }

    @Test
    fun `toEither should return failure value`() {
        val value: Nothing? = null
        val actual = value.toEither()
        val expected = Either.Failure<Nothing, Nothing?>(null)
        expectThat(actual) isEqualTo expected
        expectThat(actual.failure).isNull()
    }

}
package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull


class FirstSuccessFn {

    @Test
    fun `should be null when empty`() {
        val list = emptyList<Either<*, *>>()

        val actual = list.firstSuccess()
        expectThat(actual).isNull()
    }

    @Test
    fun `should not find when all are success`() {
        val list = listOf(failure(1), failure(2), failure(3))
        val actual = list.firstSuccess()
        expectThat(actual).isNull()
    }


    @Test
    fun `should find when all are success`() {
        val list = listOf(success(1), success(2), success(3))
        val actual = list.firstSuccess()
        expectThat(actual) isEqualTo 1
    }


    @Test
    fun `should find when some are success`() {
        val list = listOf(failure("1"), success(2), failure("3"))
        val actual = list.firstSuccess()
        expectThat(actual) isEqualTo 2
    }


}
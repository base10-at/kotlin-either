package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.map.map
import at.base10.either.map.mapFailure
import at.base10.either.success
import at.base10.either.value.failureOrNull
import at.base10.either.value.orNull
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class CollectIteratorFn {
    @Test
    fun `should be success when empty`() {
        val list = emptyList<Either<*, *>>()

        val actual = list.iterator().collectApplicative()

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo success(emptyList())
    }

    @Test
    fun `should collect applicative when all success`() {
        val list = listOf(success(1), success(2), success(3))

        val actual = list.iterator().collectApplicative()

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo success(listOf(1, 2, 3))
    }

    @Test
    fun `should collect applicative when some are failure`() {
        val list = listOf(success("1"), failure(2), failure(3))
        val actual = list.iterator().collectApplicative()
        expectThat(actual.failureOrNull()).isA<Iterator<*>>()
        expectThat(actual.mapFailure { it.asSequence().toList() }) isEqualTo failure(listOf(2, 3))
    }

    @Test
    fun `should collect monadic when all success`() {
        val list = listOf(success(1), success(2), success(3))
        val actual = list.iterator().collectMonadic()
        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo success(listOf(1, 2, 3))
    }

    @Test
    fun `should collect monadic when some are failure`() {
        val list = listOf(success("1"), failure(2), failure(3))
        val actual = list.iterator().collectMonadic()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual.failureOrNull()).not().isA<Iterator<*>>()
        expectThat(actual) isEqualTo failure(2)

    }


}
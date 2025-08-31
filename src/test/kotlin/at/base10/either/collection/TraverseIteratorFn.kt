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


class TraverseIteratorFn {

    @Test
    fun `should be success when empty`() {
        val list = emptyList<Any>()

        val actual = list.iterator().traverseApplicative { success(it) }

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo success(emptyList())
    }

    @Test
    fun `should traverse applicative when all success`() {
        val list = listOf(1, 2, 3)

        val actual = list.iterator().traverseApplicative { success(it + 1) }

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo success(listOf(2, 3, 4))

    }

    @Test
    fun `should traverse applicative when some are failure`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) success(it) else failure(
                it
            )
        }

        val actual = list.iterator().traverseApplicative(mapping)

        expectThat(actual.failureOrNull()).isA<Iterator<*>>()
        expectThat(actual.mapFailure { it.asSequence().toList() }) isEqualTo failure(listOf(2, 3).toList())

    }

    @Test
    fun `should traverse monadic when all success`() {
        val list = listOf(1, 2, 3)

        val actual = list.iterator().traverseMonadic { success(it + 1) }

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo success(listOf(2, 3, 4))

    }

    @Test
    fun `should traverse monadic when some are failure`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) success(it) else failure(
                it
            )
        }

        val actual = list.iterator().traverseMonadic(mapping)

        expectThat(actual.failureOrNull()).not().isA<Iterator<*>>()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual) isEqualTo failure(2)

    }
}
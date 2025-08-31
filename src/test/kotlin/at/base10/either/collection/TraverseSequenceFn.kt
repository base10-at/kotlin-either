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


class TraverseSequenceFn {
    @Test
    fun `should be success when empty`() {
        val list = emptyList<Any>()

        val actual = list.asSequence().traverseApplicative { success(it) }

        expectThat(actual.orNull()).isA<Sequence<*>>()
        expectThat(actual.map { it.toList() }) isEqualTo success(emptyList())
    }

    @Test
    fun `should traverse applicative when all success`() {
        val list = listOf(1, 2, 3)

        val actual = list.asSequence().traverseApplicative { success(it + 1) }

        expectThat(actual.orNull()).isA<Sequence<*>>()
        expectThat(actual.map { it.toList() }) isEqualTo success(listOf(2, 3, 4))
    }

    @Test
    fun `should traverse applicative when some are failure`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) success(it) else failure(
                it
            )
        }

        val actual = list.asSequence().traverseApplicative(mapping)

        expectThat(actual.failureOrNull()).isA<Sequence<*>>()
        expectThat(actual.mapFailure { it.toList() }) isEqualTo failure(listOf(2, 3))
    }

    @Test
    fun `should traverse monadic when all success`() {
        val list = listOf(1, 2, 3)

        val actual = list.asSequence().traverseMonadic { success(it + 1) }

        expectThat(actual.orNull()).isA<Sequence<*>>()
        expectThat(actual.map { it.toList() }) isEqualTo success(listOf(2, 3, 4))
    }

    @Test
    fun `should traverse monadic when some are failure`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) success(it) else failure(
                it
            )
        }

        val actual = list.asSequence().traverseMonadic(mapping)

        expectThat(actual.failureOrNull()).not().isA<Sequence<*>>()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual) isEqualTo failure(2)
    }
}
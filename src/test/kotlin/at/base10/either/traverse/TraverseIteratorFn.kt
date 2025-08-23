package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.map.mapFailure
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

        val actual = list.iterator().traverse.applicative { Either.success(it) }

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo Either.success(emptyList())
    }

    @Test
    fun `should traverse applicative when all when success`() {
        val list = listOf(1, 2, 3)

        val actual = list.iterator().traverse.applicative { Either.success(it + 1) }

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo Either.success(listOf(2, 3, 4))

    }

    @Test
    fun `should traverse applicative when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) Either.success(it) else Either.failure(
                it
            )
        }

        val actual = list.iterator().traverse.applicative(mapping)

        expectThat(actual.failureOrNull()).isA<Iterator<*>>()
        expectThat(actual.mapFailure { it.asSequence().toList() }) isEqualTo Either.failure(listOf(2, 3).toList())

    }

    @Test
    fun `should traverse monadic when all when success`() {
        val list = listOf(1, 2, 3)

        val actual = list.iterator().traverse.monadic { Either.success(it + 1) }

        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo Either.success(listOf(2, 3, 4))

    }

    @Test
    fun `should traverse monadic when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) Either.success(it) else Either.failure(
                it
            )
        }

        val actual = list.iterator().traverse.monadic(mapping)

        expectThat(actual.failureOrNull()).not().isA<Iterator<*>>()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual) isEqualTo Either.failure(2)

    }
}
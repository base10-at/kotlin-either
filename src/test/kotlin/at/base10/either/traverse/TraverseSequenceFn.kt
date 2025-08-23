package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.failureOrNull
import at.base10.either.map.map
import at.base10.either.map.mapFailure
import at.base10.either.orNull
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class TraverseSequenceFn {
    @Test
    fun `should traverse applicative when all when success`() {
        val list = listOf(1, 2, 3)

        val actual = list.asSequence().traverse.applicative { Either.success(it + 1) }

        expectThat(actual.orNull()).isA<Sequence<*>>()
        expectThat(actual.map { it.toList() }) isEqualTo Either.success(listOf(2, 3, 4))
    }

    @Test
    fun `should traverse applicative when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) Either.success(it) else Either.failure(
                it
            )
        }

        val actual = list.asSequence().traverse.applicative(mapping)

        expectThat(actual.failureOrNull()).isA<Sequence<*>>()
        expectThat(actual.mapFailure { it.toList() }) isEqualTo Either.failure(listOf(2, 3))
    }

    @Test
    fun `should traverse monadic when all when success`() {
        val list = listOf(1, 2, 3)

        val actual = list.asSequence().traverse.monadic { Either.success(it + 1) }

        expectThat(actual.orNull()).isA<Sequence<*>>()
        expectThat(actual.map { it.toList() }) isEqualTo Either.success(listOf(2, 3, 4))
    }

    @Test
    fun `should traverse monadic when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) Either.success(it) else Either.failure(
                it
            )
        }

        val actual = list.asSequence().traverse.monadic(mapping)

        expectThat(actual.failureOrNull()).not().isA<Sequence<*>>()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual) isEqualTo Either.failure(2)
    }
}
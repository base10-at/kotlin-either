package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.failureOrNull
import at.base10.either.orNull
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class TraverseListFn {
    @Test
    fun `should traverse applicative when all when success`() {
        val list = listOf(1, 2, 3)

        val actual = list.traverse.applicative { Either.success(it + 1) }

        expectThat(actual.orNull()).isA<List<*>>()
        expectThat(actual) isEqualTo Either.success(listOf(2, 3, 4))
    }

    @Test
    fun `should traverse applicative when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) Either.success(it) else Either.failure(
                it
            )
        }

        val actual = list.traverse.applicative(mapping)

        expectThat(actual.failureOrNull()).isA<List<*>>()
        expectThat(actual) isEqualTo Either.failure(listOf(2, 3))
    }

    @Test
    fun `should traverse monadic when all when success`() {
        val list = listOf(1, 2, 3)

        val actual = list.traverse.monadic { Either.success(it + 1) }

        expectThat(actual.orNull()).isA<List<*>>()
        expectThat(actual) isEqualTo Either.success(listOf(2, 3, 4))
    }

    @Test
    fun `should traverse monadic when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = {
            if (it <= 1) Either.success(it) else Either.failure(
                it
            )
        }

        val actual = list.traverse.monadic(mapping)

        expectThat(actual.failureOrNull()).not().isA<List<*>>()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual) isEqualTo Either.failure(2)
    }
}
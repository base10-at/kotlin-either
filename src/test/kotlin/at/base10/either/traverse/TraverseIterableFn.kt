package at.base10.either.traverse

import at.base10.either.Either
import at.base10.either.map.map
import at.base10.either.value.failureOrNull
import at.base10.either.value.orNull
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class TraverseIterableFn {


    @Test
    fun `should be success when empty`() {
        val list = emptyList<Any>()

        val actual = list.asIterable().traverseApplicative { Either.success(it) }

        expectThat(actual.orNull()).isA<Iterable<*>>()
        expectThat(actual.map { it.toList() }) isEqualTo Either.success(emptyList())
    }


    @Test
    fun `should traverse applicative when all when success`() {
        val list = listOf(1, 2, 3)
        val actual = list.asIterable().traverseApplicative { Either.success(it + 1) }
        expectThat(actual.orNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.success(listOf(2, 3, 4))
    }

    @Test
    fun `should traverse applicative when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = { if (it <= 1) Either.success(it) else Either.failure(it) }
        val actual = list.asIterable().traverseApplicative(mapping)
        expectThat(actual.failureOrNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.failure(listOf(2, 3).toList())

    }

    @Test
    fun `should traverse monadic when all when success`() {
        val list = listOf(1, 2, 3)

        val actual = list.asIterable().traverseMonadic { Either.success(it + 1) }
        expectThat(actual.orNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.success(listOf(2, 3, 4))

    }

    @Test
    fun `should traverse monadic when some are failure when success`() {
        val list = listOf(1, 2, 3)
        val mapping: (Int) -> Either<Int, Int> = { if (it <= 1) Either.success(it) else Either.failure(it) }

        val actual = list.asIterable().traverseMonadic(mapping)

        expectThat(actual.failureOrNull()).not().isA<Iterable<*>>()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual) isEqualTo Either.failure(2)
    }
}
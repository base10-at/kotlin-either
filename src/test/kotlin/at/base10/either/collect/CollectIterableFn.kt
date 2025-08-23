package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.value.failureOrNull
import at.base10.either.value.orNull
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class CollectIterableFn {

    @Test
    fun `should be success when empty`() {
        val list = emptyList<Either<*, *>>()

        val actual = list.asIterable().collect.applicative()
        expectThat(actual.orNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.success(emptyList())
    }

    @Test
    fun `should be success when init with empty`() {
        val list = listOf(Either.success(1), Either.success(2), Either.success(3))

        val actual = IterableCollector(list).applicative()
        expectThat(actual.orNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.success(listOf(1, 2, 3))
    }


    @Test
    fun `should collect applicative when all when success`() {
        val list = listOf(Either.success(1), Either.success(2), Either.success(3))
        val actual = list.asIterable().collect.applicative()
        expectThat(actual.orNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.success(listOf(1, 2, 3))

    }

    @Test
    fun `should collect applicative when some are failure when success`() {
        val list = listOf(Either.success("1"), Either.failure(2), Either.failure(3))
        val actual = list.asIterable().collect.applicative()
        expectThat(actual.failureOrNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.failure(listOf(2, 3))

    }

    @Test
    fun `should collect monadic when all when success`() {
        val list = listOf(Either.success(1), Either.success(2), Either.success(3))
        val actual = list.asIterable().collect.monadic()
        expectThat(actual.orNull()).isA<Iterable<*>>()
        expectThat(actual) isEqualTo Either.success(listOf(1, 2, 3))

    }

    @Test
    fun `should collect monadic when some are failure when success`() {
        val list = listOf(Either.success("1"), Either.failure(2), Either.failure(3))
        val actual = list.asIterable().collect.monadic()
        expectThat(actual.failureOrNull()).not().isA<Iterable<*>>()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual) isEqualTo Either.failure(2)
    }
}
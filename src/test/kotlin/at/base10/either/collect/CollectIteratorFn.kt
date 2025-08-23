package at.base10.either.collect

import at.base10.either.Either
import at.base10.either.failureOrNull
import at.base10.either.map.map
import at.base10.either.map.mapFailure
import at.base10.either.orNull
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class CollectIteratorFn {
    @Test
    fun `should collect applicative when all when success`() {
        val list = listOf(Either.success(1), Either.success(2), Either.success(3))
        val actual = list.iterator().collect.applicative()
        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo Either.success(listOf(1, 2, 3))
    }

    @Test
    fun `should collect applicative when some are failure when success`() {
        val list = listOf(Either.success("1"), Either.failure(2), Either.failure(3))
        val actual = list.iterator().collect.applicative()
        expectThat(actual.failureOrNull()).isA<Iterator<*>>()
        expectThat(actual.mapFailure { it.asSequence().toList() }) isEqualTo Either.failure(listOf(2, 3))
    }

    @Test
    fun `should collect monadic when all when success`() {
        val list = listOf(Either.success(1), Either.success(2), Either.success(3))
        val actual = list.iterator().collect.monadic()
        expectThat(actual.orNull()).isA<Iterator<*>>()
        expectThat(actual.map { it.asSequence().toList() }) isEqualTo Either.success(listOf(1, 2, 3))
    }

    @Test
    fun `should collect monadic when some are failure when success`() {
        val list = listOf(Either.success("1"), Either.failure(2), Either.failure(3))
        val actual = list.iterator().collect.monadic()
        expectThat(actual.failureOrNull()).isA<Int>()
        expectThat(actual.failureOrNull()).not().isA<Iterator<*>>()
        expectThat(actual) isEqualTo Either.failure(2)

    }


}
package at.base10.either.collection

import at.base10.either.Either
import at.base10.either.failure
import at.base10.either.success
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class PartitionIterableFn {

    @Test
    fun `should be success when empty`() {
        val list = emptyList<Either<*, *>>()

        val actual = list.asIterable().partition()
        expectThat(actual).isA<Pair<*, *>>()
        expectThat(actual.first).isA<Iterable<*>>()
        expectThat(actual.second).isA<Iterable<*>>()
        expectThat(actual.first.toList()) isEqualTo emptyList()
        expectThat(actual.second.toList()) isEqualTo emptyList()
    }


    @Test
    fun `should collect applicative when all success`() {
        val list = listOf(success(1), success(2), success(3))
        val actual = list.asIterable().partition()
        expectThat(actual).isA<Pair<*, *>>()
        expectThat(actual.first).isA<Iterable<*>>()
        expectThat(actual.second).isA<Iterable<*>>()
        expectThat(actual.first.toList()) isEqualTo listOf(1, 2, 3)
        expectThat(actual.second.toList()) isEqualTo emptyList()

    }


    @Test
    fun `should collect applicative when all failure`() {
        val list = listOf(failure(1), failure(2), failure(3))
        val actual = list.asIterable().partition()
        expectThat(actual).isA<Pair<*, *>>()
        expectThat(actual.first).isA<Iterable<*>>()
        expectThat(actual.second).isA<Iterable<*>>()
        expectThat(actual.second.toList()) isEqualTo listOf(1, 2, 3)
        expectThat(actual.first.toList()) isEqualTo emptyList()

    }

    @Test
    fun `should collect monadic when some are failure`() {
        val list = listOf(success("1"), failure(2), failure(3))
        val actual = list.asIterable().partition()
        expectThat(actual).isA<Pair<*, *>>()
        expectThat(actual.first).isA<Iterable<*>>()
        expectThat(actual.second).isA<Iterable<*>>()
        expectThat(actual.first.toList()) isEqualTo listOf("1")
        expectThat(actual.second.toList()) isEqualTo listOf(2, 3)
    }

}
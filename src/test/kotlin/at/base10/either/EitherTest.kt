package at.base10.either

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class EitherTest {

    @Nested
    inner class EitherFn {

        @Test
        fun `either should return success value`() {
            val subject = Either.Companion.success("success")

            val actual = subject.either(
                onSuccess = { it },
                onFailure = { "FAILURE" },
            )
            expectThat(actual) isEqualTo "success"
        }

        @Test
        fun `either should return failure value`() {

            val subject = Either.Companion.failure("failure")

            val actual = subject.either(
                onSuccess = { "SUCCESS" },
                onFailure = { it },
            )
            expectThat(actual) isEqualTo "failure"

        }
    }

    @Nested
    inner class MapFn {

        @Test
        fun `should map value when success`() {
            val subject = Either.Companion.success(1)
            val actual = subject.map { it + 1 }
            expectThat(actual) isEqualTo Either.Companion.success(2)
        }

        @Test
        fun `should not map value when failure`() {
            val subject: Either<Int, String> = Either.Companion.failure("failure")
            val actual = subject.map { it + 1 }
            expectThat(actual) isEqualTo Either.Companion.failure("failure")
        }
    }

    @Nested
    inner class MapFailureFn {

        @Test
        fun `should map value when failure`() {
            val subject = Either.Companion.failure(1)
            val actual = subject.mapFailure { it + 1 }
            expectThat(actual) isEqualTo Either.Companion.failure(2)
        }

        @Test
        fun `should not map value when success`() {
            val subject: Either<String, Int> = Either.Companion.success("success")
            val actual = subject.mapFailure { it + 1 }
            expectThat(actual) isEqualTo Either.Companion.success("success")
        }
    }

    @Nested
    inner class MapEitherFn {

        @Test
        fun `should map value when success`() {
            val subject = Either.Companion.failure(1)
            val actual = subject.mapEither(
                onSuccess = { 0 },
                onFailure = { it + 1 },
            )
            expectThat(actual) isEqualTo Either.Companion.failure(2)
        }

        @Test
        fun `should not map value when success`() {
            val subject = Either.Companion.success(1)
            val actual = subject.mapEither(
                onSuccess = { it + 1 },
                onFailure = { 0 },
            )
            expectThat(actual) isEqualTo Either.Companion.success(2)
        }
    }

    @Nested
    inner class BindFn {

        @Test
        fun `should bind to Success value when success`() {
            val subject = Either.Companion.success(1)
            val actual = subject.bind { Either.Companion.success(it + 1) }
            expectThat(actual) isEqualTo Either.Companion.success(2)
        }

        @Test
        fun `should not bind to Success value when failure`() {
            val subject: Either<Int, String> = Either.Companion.failure("failure")
            val actual = subject.bind { Either.Companion.success(it + 1) }
            expectThat(actual) isEqualTo Either.Companion.failure("failure")
        }

        @Test
        fun `should bind to Failure value when success`() {
            val subject = Either.Companion.success(1)
            val actual = subject.bind { Either.Companion.failure(it + 1) }
            expectThat(actual) isEqualTo Either.Companion.failure(2)
        }

        @Test
        fun `should not bind to Failure value when failure`() {
            val subject: Either<Int, String> = Either.Companion.failure("failure")
            val actual = subject.bind { Either.Companion.failure("BIND") }
            expectThat(actual) isEqualTo Either.Companion.failure("failure")
        }
    }

    @Nested
    inner class BindFailureFn {


        @Test
        fun `should bindFailure to Success value when success`() {
            val subject: Either<String, Int> = Either.Companion.failure(1)
            val actual = subject.bindFailure { Either.Companion.success("BIND") }
            expectThat(actual) isEqualTo Either.Companion.success("BIND")
        }

        @Test
        fun `should not bindFailure to Success value when failure`() {
            val subject: Either<String, Int> = Either.Companion.success("success")
            val actual = subject.bindFailure { Either.Companion.success("BIND") }
            expectThat(actual) isEqualTo Either.Companion.success("success")
        }

        @Test
        fun `should bindFailure to Failure value when success`() {
            val subject: Either<String, Int> = Either.Companion.failure(1)
            val actual = subject.bindFailure { Either.Companion.failure(it + 1) }
            expectThat(actual) isEqualTo Either.Companion.failure(2)
        }

        @Test
        fun `should not bindFailure to Failure value when failure`() {
            val subject: Either<String, Int> = Either.Companion.success("success")
            val actual = subject.bindFailure { Either.Companion.failure("BIND") }
            expectThat(actual) isEqualTo Either.Companion.success("success")
        }
    }

    @Nested
    inner class BindEitherFn {

        @Test
        fun `should bindFailure to Success value when success`() {
            val subject: Either<String, Int> = Either.Companion.failure(1)
            val actual = subject.bindEither(
                onSuccess = { Either.Companion.success("SUCCESS") },
                onFailure = { Either.Companion.success("FAILURE") }
            )
            expectThat(actual) isEqualTo Either.Companion.success("FAILURE")
        }

        @Test
        fun `should not bindFailure to Success value when failure`() {
            val subject: Either<String, Int> = Either.Companion.success("success")
            val actual = subject.bindEither(
                onSuccess = { Either.Companion.success("SUCCESS") },
                onFailure = { Either.Companion.success("FAILURE") }
            )
            expectThat(actual) isEqualTo Either.Companion.success("SUCCESS")
        }


        @Test
        fun `should bindFailure to Failure value when success`() {
            val subject: Either<String, Int> = Either.Companion.failure(1)
            val actual = subject.bindEither(
                onSuccess = { Either.Companion.failure("SUCCESS") },
                onFailure = { Either.Companion.failure("FAILURE") }
            )
            expectThat(actual) isEqualTo Either.Companion.failure("FAILURE")
        }

        @Test
        fun `should not bindFailure to Failure value when failure`() {
            val subject: Either<String, Int> = Either.Companion.success("success")
            val actual = subject.bindEither(
                onSuccess = { Either.Companion.failure("SUCCESS") },
                onFailure = { Either.Companion.failure("FAILURE") }
            )
            expectThat(actual) isEqualTo Either.Companion.failure("SUCCESS")
        }

    }


    @Nested
    inner class SwapFn {

        @Test
        fun `should swap values when success`() {
            expectThat(Either.Companion.success(1).swap()) isEqualTo Either.Companion.failure(1)
            expectThat(Either.Companion.failure(2).swap()) isEqualTo Either.Companion.success(2)
        }
    }

    @Nested
    inner class GetSuccessFn {

        @Test
        fun `should get success when success`() {
            val actual = Either.Companion.success(1).success
            expectThat(actual) isEqualTo 1
        }
    }


    @Nested
    inner class GetFailureFn {
        @Test
        fun `should get failure when failure`() {
            val actual = Either.Companion.failure(1).failure
            expectThat(actual) isEqualTo 1
        }
    }

    @Nested
    inner class ToNullableFn {
        @Test
        fun `should get value when success`() {
            val actual = Either.Companion.success(1).toNullable()
            expectThat(actual) isEqualTo 1
        }

        @Test
        fun `should get null when failure`() {
            val actual = Either.Companion.failure(1).toNullable()
            expectThat(actual) isEqualTo null
        }
    }

    @Nested
    inner class FailureOrNullFn {

        @Test
        fun `should get value when success`() {
            val actual = Either.Companion.failure(1).failureOrNull()
            expectThat(actual) isEqualTo 1
        }

        @Test
        fun `should get null when failure`() {
            val actual = Either.Companion.success(1).failureOrNull()
            expectThat(actual) isEqualTo null
        }
    }

    @Nested
    inner class OrElseFn {
        @Test
        fun `should get value when success`() {
            val actual = Either.Companion.success(1).orElse { f -> 2 }
            expectThat(actual) isEqualTo 1
        }

        @Test
        fun `should map value when failure`() {
            val actual = Either.Companion.failure(1).orElse { it * 2 }
            expectThat(actual) isEqualTo 2
        }
    }

    @Nested
    inner class RecoverFn {
        @Test
        fun `should recover when success`() {
            val actual = Either.Companion.success(1).recover { f -> 2 }
            expectThat(actual) isEqualTo Either.Companion.success(1)
        }

        @Test
        fun `should recover when failure`() {
            val actual = Either.Companion.failure(1).recover { it * 2 }
            expectThat(actual) isEqualTo Either.Companion.success(2)
        }
    }

    @Nested
    inner class CountFn {
        @Test
        fun `should count when success`() {
            val actual = Either.Companion.success(1).count
            expectThat(actual) isEqualTo 1
        }

        @Test
        fun `should count when failure`() {
            val actual = Either.Companion.failure(1).count
            expectThat(actual) isEqualTo 0
        }
    }

    @Nested
    inner class SequenceFn {
        @Test
        fun `should sequence applicative when all when success`() {
            val list = listOf(Either.Companion.success(1), Either.Companion.success(2), Either.Companion.success(3))
            expectThat(list.sequence.applicative()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
            expectThat(list.iterator().sequence.applicative()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
            expectThat(list.asIterable().sequence.applicative()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
            expectThat(list.asSequence().sequence.applicative()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
        }

        @Test
        fun `should sequence applicative when some are failure when success`() {
            val list = listOf(Either.Companion.success("1"), Either.Companion.failure(2), Either.Companion.failure(3))
            expectThat(list.sequence.applicative()) isEqualTo Either.Companion.failure(listOf(2, 3))
            expectThat(list.iterator().sequence.applicative()) isEqualTo Either.Companion.failure(listOf(2, 3))
            expectThat(list.asIterable().sequence.applicative()) isEqualTo Either.Companion.failure(listOf(2, 3))
            expectThat(list.asSequence().sequence.applicative()) isEqualTo Either.Companion.failure(listOf(2, 3))
        }

        @Test
        fun `should sequence monadic when all when success`() {
            val list = listOf(Either.Companion.success(1), Either.Companion.success(2), Either.Companion.success(3))
            expectThat(list.sequence.monadic()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
            expectThat(list.iterator().sequence.monadic()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
            expectThat(list.asIterable().sequence.monadic()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
            expectThat(list.asSequence().sequence.monadic()) isEqualTo Either.Companion.success(listOf(1, 2, 3))
        }

        @Test
        fun `should sequence monadic when some are failure when success`() {
            val list = listOf(Either.Companion.success("1"), Either.Companion.failure(2), Either.Companion.failure(3))
            expectThat(list.sequence.monadic()) isEqualTo Either.Companion.failure(2)
            expectThat(list.iterator().sequence.monadic()) isEqualTo Either.Companion.failure(2)
            expectThat(list.asIterable().sequence.monadic()) isEqualTo Either.Companion.failure(2)
            expectThat(list.asSequence().sequence.monadic()) isEqualTo Either.Companion.failure(2)
        }
    }

    @Nested
    inner class TraverseFn {
        @Test
        fun `should traverse applicative when all when success`() {
            val list = listOf(1, 2, 3)
            expectThat(list.traverse.applicative { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
            expectThat(list.iterator().traverse.applicative { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
            expectThat(list.asIterable().traverse.applicative { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
            expectThat(list.asSequence().traverse.applicative { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
        }

        @Test
        fun `should traverse applicative when some are failure when success`() {
            val list = listOf(1, 2, 3)
            val mapping: (Int) -> Either<Int, Int> = { if (it <= 1) Either.Companion.success(it) else Either.Companion.failure(
                it
            )
            }
            expectThat(list.traverse.applicative(mapping)) isEqualTo Either.Companion.failure(listOf(2, 3))
            expectThat(list.iterator().traverse.applicative(mapping)) isEqualTo Either.Companion.failure(
                listOf(
                    2,
                    3
                ).toList()
            )
            expectThat(list.asIterable().traverse.applicative(mapping)) isEqualTo Either.Companion.failure(
                listOf(
                    2,
                    3
                ).toList()
            )
            expectThat(list.asSequence().traverse.applicative(mapping)) isEqualTo Either.Companion.failure(listOf(2, 3))
        }

        @Test
        fun `should traverse monadic when all when success`() {
            val list = listOf(1, 2, 3)
            expectThat(list.traverse.monadic { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
            expectThat(list.iterator().traverse.monadic { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
            expectThat(list.asIterable().traverse.monadic { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
            expectThat(list.asSequence().traverse.monadic { Either.Companion.success(it + 1) }) isEqualTo Either.Companion.success(
                listOf(2, 3, 4)
            )
        }

        @Test
        fun `should traverse monadic when some are failure when success`() {
            val list = listOf(1, 2, 3)
            val mapping: (Int) -> Either<Int, Int> = { if (it <= 1) Either.Companion.success(it) else Either.Companion.failure(
                it
            )
            }
            expectThat(list.traverse.monadic(mapping)) isEqualTo Either.Companion.failure(2)
            expectThat(list.iterator().traverse.monadic(mapping)) isEqualTo Either.Companion.failure(2)
            expectThat(list.asIterable().traverse.monadic(mapping)) isEqualTo Either.Companion.failure(2)
            expectThat(list.asSequence().traverse.monadic(mapping)) isEqualTo Either.Companion.failure(2)
        }
    }

}
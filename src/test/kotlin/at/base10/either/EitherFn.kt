package at.base10.either

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class EitherFn {

    @Test
    fun `either should return success value`() {
        val subject: Either<String, String> = Either.success("success")

        val actual = subject.either(
            onSuccess = { "$it SUCCESS" },
            onFailure = { "$it FAILURE" },
        )
        expectThat(actual) isEqualTo "success SUCCESS"
    }

    @Test
    fun `either should return failure value`() {

        val subject: Either<String, String> = Either.failure("failure")

        val actual = subject.either(
            onSuccess = { "$it SUCCESS" },
            onFailure = { "$it FAILURE" },
        )
        expectThat(actual) isEqualTo "failure FAILURE"

    }
}
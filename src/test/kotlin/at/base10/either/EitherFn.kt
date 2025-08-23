package at.base10.either

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class EitherFn {

    @Test
    fun `either should return success value`() {
        val subject = Either.success("success")

        val actual = subject.either(
            onSuccess = { it },
            onFailure = { "FAILURE" },
        )
        expectThat(actual) isEqualTo "success"
    }

    @Test
    fun `either should return failure value`() {

        val subject = Either.failure("failure")

        val actual = subject.either(
            onSuccess = { "SUCCESS" },
            onFailure = { it },
        )
        expectThat(actual) isEqualTo "failure"

    }
}
package at.base10.either.value

import at.base10.either.Either

val <S, F> Either<S, F>.isFailure: Boolean get() = !this.isSuccess

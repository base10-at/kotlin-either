package at.base10.either

val <S, F> Either<S, F>.isFailure: Boolean get() = !this.isSuccess

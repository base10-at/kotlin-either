package at.base10.either.collect

import at.base10.either.Either

fun <S, F> Iterable<Either<S, F>>.collectApplicative(): Either<Iterable<S>, Iterable<F>> {
    val successList = mutableListOf<S>()
    val failureList = mutableListOf<F>()

    for (element in this) {
        when (element) {
            is Either.Failure -> failureList.add(element.value)
            else -> successList.add((element as Either.Success<S, *>).value)
        }
    }
    return if (failureList.isNotEmpty()) {
        Either.failure(failureList.toList())
    } else {
        Either.success(successList.toList())
    }
}

fun <S, F> Iterable<Either<S, F>>.collectMonadic(): Either<Iterable<S>, F> {
    val successList = mutableListOf<S>()
    for (element in this) {
        when (element) {
            is Either.Failure -> return Either.failure(element.value)
            else -> successList.add((element as Either.Success<S, *>).value)
        }
    }
    return Either.success(successList)
}

package at.base10

import at.base10.either.Either
import at.base10.either.bind
import at.base10.either.bindEither
import at.base10.either.map
import at.base10.either.mapEither
import at.base10.either.mapFailure

fun Either<Int, String>.doSomthing(): Either<Int, String> {
    return this.bindEither(
        { Either.success(it) },
        { Either.failure(it) }
    )
        .map { it + 2 }
        .mapFailure { it + 2 }
        .mapEither(
            { it + 3 },
            { it + 3 },
        )
        .bind { Either.failure(("'$it'")) }

}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    val x: Either<Int, String> = Either.failure("hello").doSomthing()
    val x = Either.success(1)
    val y = x.doSomthing()
    val name = y.either(
        { x -> x },
        { x -> x }
    )
    println(name)
}

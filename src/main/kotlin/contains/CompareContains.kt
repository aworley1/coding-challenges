package contains

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    val randomNumbers = List(100000000) { Random.nextInt(-10000, 10000) }.sorted()
    println("Sorted Numbers")

    val numberToLookFor = Random.nextInt(-10000, 10000)

    val howLongLibrary = measureTimeMillis {
        randomNumbers.contains(numberToLookFor)
    }
    println("Library Contains: $howLongLibrary")

    val howLongSimpleContains = measureTimeMillis {
        randomNumbers.simpleContains(numberToLookFor)
    }
    println("Simple Contains: $howLongSimpleContains (${howLongSimpleContains.toDouble()/howLongLibrary.toDouble()}x as long)")

    val howLongBinaryContains = measureTimeMillis {
        randomNumbers.binaryContains(numberToLookFor)
    }
    println("Binary Contains: $howLongBinaryContains (${howLongBinaryContains.toDouble()/howLongLibrary.toDouble()}x as long)")

}
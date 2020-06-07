package sorts

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    val randomNumbers = List(10000) { Random.nextInt(-10000, 10000) }

    val howLongLibrary = measureTimeMillis {
        randomNumbers.sorted()
    }

    val howLongMergeSort = measureTimeMillis {
        mergeSort(randomNumbers)
    }

    val howLongInsertationSort = measureTimeMillis {
        insertationSort(randomNumbers)
    }

    println("Library Sort: $howLongLibrary")
    println("Merge Sort: $howLongMergeSort (${howLongMergeSort.toDouble()/howLongLibrary.toDouble()}x as long)")
    println("Insertation Sort: $howLongInsertationSort (${howLongInsertationSort.toDouble()/howLongLibrary.toDouble()}x as long)")
}
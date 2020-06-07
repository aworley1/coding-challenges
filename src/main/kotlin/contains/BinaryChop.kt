package contains

fun List<Int>.binaryContains(element: Int): Boolean {
//    println("List: $this, element: $element")

    val midpoint = this.size / 2
    return when {
        this.isEmpty() -> false
        this.size == 1 -> this.single() == element
        this[midpoint] == element -> true
        this[midpoint] > element -> this.subList(0, midpoint).binaryContains(element)
        this[midpoint] < element -> this.subList(midpoint, this.size).binaryContains(element)
        else -> throw RuntimeException("Impossible")
    }
}
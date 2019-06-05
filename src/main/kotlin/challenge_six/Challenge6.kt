package challenge_six

fun myFilter(input: List<Int>, condition: (Int) -> Boolean): List<Int> {
    if (input.isEmpty()) return emptyList()

    if (input.size == 1) {
        return elementIfItMatchesCondition(input.single(), condition)
    }

    val firstItemFiltered = elementIfItMatchesCondition(input[0], condition)

    return firstItemFiltered + myFilter(input.drop(1), condition)

}

fun elementIfItMatchesCondition(element: Int, condition: (Int) -> Boolean): List<Int> {
    return if (condition(element)) listOf(element) else emptyList()
}
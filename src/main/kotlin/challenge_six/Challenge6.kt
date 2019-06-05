package challenge_six

fun myFilter(input: List<Int>, condition: (Int) -> Boolean): List<Int> {
    return when (input.size) {
        0 -> emptyList()
        1 -> elementIfItMatchesCondition(input.single(), condition)
        else -> elementIfItMatchesCondition(input.first(), condition) + myFilter(input.drop(1), condition)
    }
}

fun elementIfItMatchesCondition(element: Int, condition: (Int) -> Boolean): List<Int> {
    return if (condition(element)) listOf(element) else emptyList()
}
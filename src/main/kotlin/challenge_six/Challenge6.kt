package challenge_six

fun <T> myFilter(input: List<T>, condition: (T) -> Boolean): List<T> {
    return when (input.size) {
        0 -> emptyList()
        1 -> elementIfItMatchesCondition(input.single(), condition)
        else -> elementIfItMatchesCondition(input.first(), condition) + myFilter(input.drop(1), condition)
    }
}

fun <T> elementIfItMatchesCondition(element: T, condition: (T) -> Boolean): List<T> {
    return if (condition(element)) listOf(element) else emptyList()
}
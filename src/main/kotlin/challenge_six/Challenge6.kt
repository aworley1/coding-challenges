package challenge_six

fun <T> List<T>.myFilter(condition: (T) -> Boolean): List<T> {
    return when (this.size) {
        0 -> emptyList()
        1 -> elementIfItMatchesCondition(this.single(), condition)
        else -> elementIfItMatchesCondition(this.first(), condition) + this.drop(1).myFilter(condition)
    }
}

fun <T> elementIfItMatchesCondition(element: T, condition: (T) -> Boolean): List<T> {
    return if (condition(element)) listOf(element) else emptyList()
}
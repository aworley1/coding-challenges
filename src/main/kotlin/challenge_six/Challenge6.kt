package challenge_six

fun myfilter(input: List<Int>, condition: (Int) -> Boolean): List<Int> {
    val shouldReturnList = condition(0)
    return if (shouldReturnList) input else emptyList()
}
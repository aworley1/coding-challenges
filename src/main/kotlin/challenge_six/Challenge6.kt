package challenge_six

fun myFilter(input: List<Int>, condition: (Int) -> Boolean): List<Int> {
    if (input.isEmpty()) return emptyList()

    if (input.size == 1) {
        if (condition(input.single())) return listOf(input.single()) else return emptyList()
    }

    val firstItemFiltered = if (condition(input[0])) listOf(input[0]) else emptyList()

    return firstItemFiltered + myFilter(input.drop(1), condition)

}
package challenge_six

fun myfilter(input: List<Int>, condition: (Int) -> Boolean): List<Int> {
    val output = mutableListOf<Int>()

    input.forEach {
        if (condition(it)) output.add(it)
    }

    return output.toList()
}
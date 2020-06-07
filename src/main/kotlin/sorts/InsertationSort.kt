package sorts

fun insertationSort(list: List<Int>): List<Int> {
    val currentList = list.toMutableList()
    val newList = mutableListOf<Int>()

    while (currentList.isNotEmpty()) {
        val min = currentList.min()!!
        currentList.remove(min)
        newList.add(min)
    }

    return newList.toList()
}
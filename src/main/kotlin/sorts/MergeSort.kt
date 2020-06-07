package sorts

fun mergeSort(list: List<Int>): List<Int> {
    if (list.size == 1) return list

    val firstHalf = list.subList(0, list.size / 2)
    val secondHalf = list.subList(list.size / 2, list.size)
    val sortedFirstHalf = mergeSort(firstHalf)
    val sortedSecondHalf = mergeSort(secondHalf)

    return merge(sortedFirstHalf, sortedSecondHalf)
}

fun merge(firstHalf: List<Int>, secondHalf: List<Int>): List<Int> {
    val output = mutableListOf<Int>()

    var i = 0
    var j = 0

    while (i < firstHalf.size || j < secondHalf.size) {
        if (i == firstHalf.size) {
            //just firstHalf left
            output.add(secondHalf[j])
            j++
        } else if (j == secondHalf.size) {
            //just secondHalf left
            output.add(firstHalf[i])
            i++
        } else {
            //some left in both
            if (firstHalf[i] < secondHalf[j]) {
                output.add(firstHalf[i])
                i++
            } else {
                output.add(secondHalf[j])
                j++
            }
        }
    }
    return output
}
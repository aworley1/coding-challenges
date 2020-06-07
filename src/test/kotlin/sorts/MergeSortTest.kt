package sorts

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class MergeSortTest {
    @Test
    fun `should merge sort`() {
        val myList = listOf(5, 3, 8, 11, 8, 5, 9, 77, -33, 5, 18, 12, 49, 50)
        val mySortedList = myList.sorted()

        assertThat(mergeSort(myList)).isEqualTo(mySortedList)
    }

    @Test
    fun `should merge two lists of 1`() {
        val list1 = listOf(1)
        val list2 = listOf(2)

        assertThat(merge(list1, list2)).isEqualTo(listOf(1,2))
        assertThat(merge(list2, list1)).isEqualTo(listOf(1,2))
    }

    @Test
    fun `should merge two lists`() {
        val list1 = listOf(1,3)
        val list2 = listOf(2)

        assertThat(merge(list1, list2)).isEqualTo(listOf(1,2,3))
        assertThat(merge(list2, list1)).isEqualTo(listOf(1,2,3))
    }
}
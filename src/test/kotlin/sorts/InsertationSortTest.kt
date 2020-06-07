package sorts

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class InsertationSortTest {
    @Test
    fun `should do inseration sort`() {
        val myList = listOf(5, 3, 8, 11, 8, 5, 9, 77, -33, 5, 18, 12, 49, 50)
        val mySortedList = myList.sorted()

        assertThat(insertationSort(myList)).isEqualTo(mySortedList)
    }
}
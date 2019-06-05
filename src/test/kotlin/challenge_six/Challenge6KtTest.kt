package challenge_six

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Challenge6KtTest {
    @Test
    fun `should return an empty list when given an empty list`() {
        val result = myfilter(emptyList()) { true }
        assertEquals(emptyList<Int>(), result)
    }

    @Test
    fun `should return all items when filter condition is true`() {
        val inputList = listOf(1, 2, 3, 4, 5, 6)
        val result = myfilter(inputList) { true }

        assertEquals(inputList, result)
    }

    @Test
    fun `should return no items when filter condition is false`() {
        val inputList = listOf(1, 2, 3, 4, 5, 6)
        val result = myfilter(inputList) { false }

        assertEquals(emptyList<Int>(), result)
    }

}
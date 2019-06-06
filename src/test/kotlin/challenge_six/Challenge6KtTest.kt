package challenge_six

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Challenge6KtTest {
    @Test
    fun `should return an empty list when given an empty list`() {
        val result = emptyList<Int>().myFilter { true }
        assertEquals(emptyList<Int>(), result)
    }

    @Test
    fun `should return all items when filter condition is true`() {
        val inputList = listOf(1, 2, 3, 4, 5, 6)
        val result = inputList.myFilter { true }

        assertEquals(inputList, result)
    }

    @Test
    fun `should return no items when filter condition is false`() {
        val inputList = listOf(1, 2, 3, 4, 5, 6)
        val result = inputList.myFilter { false }

        assertEquals(emptyList<Int>(), result)
    }

    @Test
    fun `should return even numbers`() {
        val inputList = listOf(1, 2, 3, 4, 5, 6)
        val result = inputList.myFilter { it % 2 == 0 }

        assertEquals(listOf(2, 4, 6), result)
    }

    @Test
    fun `should filter a list of strings`() {
        val inputList = listOf("a", "a", "b", "c")
        val result = inputList.myFilter { it == "a" }

        assertEquals(listOf("a", "a"), result)
    }

}
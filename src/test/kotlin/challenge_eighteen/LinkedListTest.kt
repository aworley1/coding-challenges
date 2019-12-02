package challenge_eighteen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LinkedListTest {
    @Test
    fun `should return description`() {
        val firstNode = Node("hello", Node("world"))

        val result = getDescription(firstNode)

        assertEquals("hello world null", result)
    }
}
package challenge_five

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Challenge5Test {
    @Test
    fun `should return draw if no spaces are left on board`() {
        val board = listOf("ryRY", "ryry")

        val result = getGridStatus(board)

        assertEquals("Draw", result)
    }

    @Test
    fun `should return yellow plays next if red has just played`() {
        val board = listOf("ryR.", "ryry")

        val result = getGridStatus(board)

        assertEquals("Yellow plays next", result)
    }
}
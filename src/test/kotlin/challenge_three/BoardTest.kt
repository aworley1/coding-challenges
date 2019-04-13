package challenge_three

import challenge_three.SquareType.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BoardTest {
    @Test
    fun `should return board as array of string rows`() {
        val board = Board(
            listOf(
                Row(listOf(Square(), Square(listOf(STORAGE_LOCATION)), Square(listOf(STORAGE_LOCATION, PLAYER)))),
                Row(listOf(Square(), Square(listOf(WALL)), Square()))
            )
        )

        val result = board.toArray()

        val expectedResult = listOf(
            " *P",
            " # "
        )

        assertEquals(expectedResult, result)
    }
}

internal class SquareTest {
    @Test
    fun `should convert empty square to string`() {
        val square = Square()

        assertEquals(" ", square.toString())
    }

    @Test
    fun `should convert player square to string`() {
        val square = Square(listOf(PLAYER))

        assertEquals("p", square.toString())
    }

    @Test
    fun `should convert storage location square to string`() {
        val square = Square(listOf(STORAGE_LOCATION))

        assertEquals("*", square.toString())
    }

    @Test
    fun `should convert storage location and player square to string`() {
        val square = Square(listOf(STORAGE_LOCATION, PLAYER))

        assertEquals("P", square.toString())
    }

    @Test
    fun `should convert wall square to string`() {
        val square = Square(listOf(WALL))

        assertEquals("#", square.toString())
    }
}
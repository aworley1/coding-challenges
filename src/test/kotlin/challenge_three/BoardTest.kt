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

    @Test
    fun `should create a board from a list of strings`() {
        val input = listOf(
            " *P",
            " # "
        )

        val result = Board.from(input)

        assertEquals(
            listOf(Square(), Square(listOf(STORAGE_LOCATION)), Square(listOf(PLAYER, STORAGE_LOCATION))),
            result.rows[0].squares
        )

        assertEquals(
            listOf(Square(), Square(listOf(WALL)), Square()),
            result.rows[1].squares
        )
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

    @Test
    fun `should parse empty square`() {
        assertEquals(Square(), Square.from(' '))
    }

    @Test
    fun `should parse square with wall`() {
        assertEquals(Square(listOf(WALL)), Square.from('#'))
    }

    @Test
    fun `should parse square with player`() {
        assertEquals(Square(listOf(PLAYER)), Square.from('p'))
    }

    @Test
    fun `should parse square with player and storage location`() {
        assertEquals(Square(listOf(PLAYER, STORAGE_LOCATION)), Square.from('P'))
    }

    @Test
    fun `should parse square with storage location`() {
        assertEquals(Square(listOf(STORAGE_LOCATION)), Square.from('*'))
    }
}
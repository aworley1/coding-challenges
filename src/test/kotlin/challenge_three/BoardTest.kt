package challenge_three

import challenge_three.SquareType.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BoardTest {
    @Test
    fun `should return board as array of string rows`() {
        val board = Board(
            listOf(
                Row(listOf(Square(), Square(STORAGE_LOCATION), Square(STORAGE_LOCATION_WITH_PLAYER))),
                Row(listOf(Square(), Square(WALL), Square()))
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
            listOf(Square(), Square(STORAGE_LOCATION), Square(STORAGE_LOCATION_WITH_PLAYER)),
            result.rows[0].squares
        )

        assertEquals(
            listOf(Square(), Square(WALL), Square()),
            result.rows[1].squares
        )
    }

    @Test
    fun `should get height and width of board`() {
        val input = listOf(
            " *P",
            " # "
        )

        val board = Board.from(input)

        assertEquals(2, board.getHeight())
        assertEquals(3, board.getWidth())
    }

    @Test
    fun `should get a square using coordinates`() {
        val input = listOf(
            " *P",
            " # "
        )

        val board = Board.from(input)

        assertEquals(STORAGE_LOCATION_WITH_PLAYER, board[0, 2].type)
        assertEquals(WALL, board[1, 1].type)
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
        val square = Square(PLAYER)

        assertEquals("p", square.toString())
    }

    @Test
    fun `should convert storage location square to string`() {
        val square = Square(STORAGE_LOCATION)

        assertEquals("*", square.toString())
    }

    @Test
    fun `should convert storage location and player square to string`() {
        val square = Square(STORAGE_LOCATION_WITH_PLAYER)

        assertEquals("P", square.toString())
    }

    @Test
    fun `should convert wall square to string`() {
        val square = Square(WALL)

        assertEquals("#", square.toString())
    }

    @Test
    fun `should parse empty square`() {
        assertEquals(Square(), Square.from(' '))
    }

    @Test
    fun `should parse square with wall`() {
        assertEquals(Square(WALL), Square.from('#'))
    }

    @Test
    fun `should parse square with player`() {
        assertEquals(Square(PLAYER), Square.from('p'))
    }

    @Test
    fun `should parse square with player and storage location`() {
        assertEquals(Square(STORAGE_LOCATION_WITH_PLAYER), Square.from('P'))
    }

    @Test
    fun `should parse square with storage location`() {
        assertEquals(Square(STORAGE_LOCATION), Square.from('*'))
    }
}
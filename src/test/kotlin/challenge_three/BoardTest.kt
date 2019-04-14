package challenge_three

import challenge_three.SquareType.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BoardTest {
    @Test
    fun `should return board as array of string rows`() {
        val board = Board(
            listOf(
                Square(EMPTY, 0, 0),
                Square(STORAGE_LOCATION, 0, 1),
                Square(STORAGE_LOCATION_WITH_PLAYER, 0, 2),
                Square(EMPTY, 1, 0),
                Square(WALL, 1, 1),
                Square(EMPTY, 1, 2)
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
            listOf(Square(EMPTY, 0, 0), Square(STORAGE_LOCATION, 0, 1), Square(STORAGE_LOCATION_WITH_PLAYER, 0, 2), Square(EMPTY, 1, 0), Square(WALL, 1, 1), Square(EMPTY, 1, 2)),
            result.squares
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
    fun `should parse empty square`() {
        assertEquals(EMPTY, SquareType.from(' '))
    }

    @Test
    fun `should parse square with wall`() {
        assertEquals(WALL, SquareType.from('#'))
    }

    @Test
    fun `should parse square with player`() {
        assertEquals(PLAYER, SquareType.from('p'))
    }

    @Test
    fun `should parse square with player and storage location`() {
        assertEquals(STORAGE_LOCATION_WITH_PLAYER, SquareType.from('P'))
    }

    @Test
    fun `should parse square with storage location`() {
        assertEquals(STORAGE_LOCATION, SquareType.from('*'))
    }
}
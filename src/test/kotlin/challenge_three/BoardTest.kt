package challenge_three

import challenge_three.SquareType.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
            listOf(
                Square(EMPTY, 0, 0),
                Square(STORAGE_LOCATION, 0, 1),
                Square(STORAGE_LOCATION_WITH_PLAYER, 0, 2),
                Square(EMPTY, 1, 0),
                Square(WALL, 1, 1),
                Square(EMPTY, 1, 2)
            ),
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

    @Test
    fun `should move player one square to the right`() {
        val input = listOf(
            "########",
            "#      #",
            "#  p   #",
            "########"
        )

        val board = Board.from(input)

        val result = board.move(Direction.RIGHT)

        val expectedBoard = listOf(
            "########",
            "#      #",
            "#   p  #",
            "########"
        )

        assertEquals(expectedBoard, result.toArray())

    }

    @Test
    fun `should move player one square to the left`() {
        val input = listOf(
            "########",
            "#      #",
            "#  p   #",
            "########"
        )

        val board = Board.from(input)

        val result = board.move(Direction.LEFT)

        val expectedBoard = listOf(
            "########",
            "#      #",
            "# p    #",
            "########"
        )

        assertEquals(expectedBoard, result.toArray())

    }

    @Test
    fun `should move player one square up`() {
        val input = listOf(
            "########",
            "#      #",
            "#  p   #",
            "########"
        )

        val board = Board.from(input)

        val result = board.move(Direction.UP)

        val expectedBoard = listOf(
            "########",
            "#  p   #",
            "#      #",
            "########"
        )

        assertEquals(expectedBoard, result.toArray())

    }

    @Test
    fun `should move player one square down`() {
        val input = listOf(
            "########",
            "#  p   #",
            "#      #",
            "########"
        )

        val board = Board.from(input)

        val result = board.move(Direction.DOWN)

        val expectedBoard = listOf(
            "########",
            "#      #",
            "#  p   #",
            "########"
        )

        assertEquals(expectedBoard, result.toArray())

    }

    @Test
    fun `should get the square with the player on`() {
        val board = Board(listOf(Square(EMPTY, 0, 0), Square(PLAYER, 0, 1)))

        val result = board.getPlayerSquare()

        assertEquals(PLAYER, result.type)
        assertEquals(0, result.row)
        assertEquals(1, result.col)
    }

    @Test
    fun `should not allow a player to move of the edge of the board`() {
        val board = Board(
            listOf(
                Square(EMPTY, 0, 0),
                Square(PLAYER, 0, 1),
                Square(EMPTY, 0, 2),
                Square(EMPTY, 1, 0),
                Square(EMPTY, 1, 1),
                Square(EMPTY, 1, 2)
            )
        )

        assertThrows<IllegalMoveException> { board.move(Direction.UP) }
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

    @Test
    fun `should add a player to a Square`() {
        val empty = Square(EMPTY, 0, 0)
        val storageLocation = Square(STORAGE_LOCATION, 0, 0)

        assertEquals(PLAYER, empty.addPlayer().type)
        assertEquals(STORAGE_LOCATION_WITH_PLAYER, storageLocation.addPlayer().type)

    }

    @Test
    fun `should not allow adding a player to a square with a player or a wall`() {
        val player = Square(PLAYER, 0, 0)
        val storageLocationWithPlayer = Square(STORAGE_LOCATION_WITH_PLAYER, 0, 0)
        val wall = Square(WALL, 0, 0)

        assertThrows<IllegalArgumentException> { storageLocationWithPlayer.addPlayer() }
        assertThrows<IllegalArgumentException> { player.addPlayer() }
        assertThrows<IllegalMoveException> { wall.addPlayer() }
    }

    @Test
    fun `should remove player from a Square`() {
        val player = Square(PLAYER, 0, 0)
        val storageLocationWithPlayer = Square(STORAGE_LOCATION_WITH_PLAYER, 0, 0)

        assertEquals(EMPTY, player.removePlayer().type)
        assertEquals(STORAGE_LOCATION, storageLocationWithPlayer.removePlayer().type)

    }

    @Test
    fun `should not allow removing a player from a square with no player`() {
        val wall = Square(WALL, 0, 0)
        val empty = Square(EMPTY, 0, 0)
        val storageLocation = Square(STORAGE_LOCATION, 0, 0)

        assertThrows<IllegalArgumentException> { empty.removePlayer() }
        assertThrows<IllegalArgumentException> { storageLocation.removePlayer() }
        assertThrows<IllegalArgumentException> { wall.removePlayer() }
    }
}
package challenge_three

import challenge_three.SquareType.*
import kotlin.IllegalArgumentException

enum class Direction(val code: Char, val verticalMovement: Int, val horizontalMovement: Int) {
    UP('U', -1, 0),
    DOWN('D', 1, 0),
    LEFT('L', 0, -1),
    RIGHT('R', 0, 1);

    companion object {
        fun of(code: Char): Direction? {
            return Direction.values().firstOrNull { it.code == code }
        }
    }
}

data class Board(val squares: List<Square>) {
    fun toArray(): List<String> {
        return squares.groupBy { it.row }
            .toSortedMap()
            .map { it.value.sortedBy { it.col }.map { it.toString() }.joinToString("") }
    }

    fun getHeight(): Int {
        return squares.maxBy { it.row }!!.row + 1
    }

    fun getWidth(): Int {
        return squares.maxBy { it.col }!!.col + 1
    }

    operator fun get(row: Int, column: Int): Square {
        return squares.single { it.row == row && it.col == column }
    }

    fun getPlayerSquare(): Square {
        return squares.single { it.type.isPlayer }
    }

    fun move(direction: Direction): Board {
        val playerStartSquare = getPlayerSquare()

        val playerMoveToSquare = get(
            row = playerStartSquare.row + direction.verticalMovement,
            column = playerStartSquare.col + direction.horizontalMovement
        )

        return this.removeSquare(playerStartSquare)
            .removeSquare(playerMoveToSquare)
            .addSquare(playerMoveToSquare.addPlayer())
            .addSquare(playerStartSquare.removePlayer())
    }

    private fun removeSquare(square: Square): Board {
        return Board(squares.filterNot { it === square })
    }

    private fun addSquare(square: Square): Board {
        return Board(squares + square)
    }

    companion object {
        fun from(input: List<String>): Board {
            val squares = input.mapIndexed { rowIndex, row ->
                row.toCharArray().mapIndexed { colIndex, square ->
                    Square(SquareType.from(square), rowIndex, colIndex)
                }
            }.flatten()

            return Board(squares)
        }
    }
}

data class Square(
    val type: SquareType = SquareType.EMPTY,
    val row: Int,
    val col: Int
) {
    override fun toString(): String {
        return type.toString()
    }

    fun addPlayer(): Square {
        return when (type) {
            EMPTY -> this.copy(type = PLAYER)
            STORAGE_LOCATION -> this.copy(type = STORAGE_LOCATION_WITH_PLAYER)
            WALL -> throw IllegalMoveException("Cannot add a player to a wall")
            PLAYER -> throw IllegalArgumentException("Cannot add a player to a player")
            STORAGE_LOCATION_WITH_PLAYER -> throw IllegalArgumentException("Cannot add a player to a player")
        }
    }

    fun removePlayer(): Square {
        return when (type) {
            EMPTY -> throw IllegalArgumentException("Cannot remove a player from this square")
            STORAGE_LOCATION -> throw IllegalArgumentException("Cannot remove a player from this square")
            WALL -> throw IllegalArgumentException("Cannot remove a player from this square")
            PLAYER -> this.copy(type = EMPTY)
            STORAGE_LOCATION_WITH_PLAYER -> this.copy(type = STORAGE_LOCATION)
        }
    }
}

enum class SquareType(val code: Char, val isPlayer: Boolean) {
    EMPTY(' ', false),
    PLAYER('p', true),
    WALL('#', false),
    STORAGE_LOCATION('*', false),
    STORAGE_LOCATION_WITH_PLAYER('P', true);

    override fun toString(): String {
        return code.toString()
    }

    companion object {
        fun from(square: Char): SquareType {
            return values().single { it.code == square }
        }
    }
}

fun processSokobanMove(input: List<String>, move: Char): List<String> {
    val board = Board.from(input)
    val direction = Direction.of(move.toUpperCase()) ?: return board.toArray()

    val rowOfPlayer = findPlayerRow(board)
    val colOfPlayer = findPlayerColumn(board)

    val newColOfPlayer = colOfPlayer + direction.horizontalMovement
    val newRowOfPlayer = rowOfPlayer + direction.verticalMovement

    if (moveIsIllegal(board, newRowOfPlayer, newColOfPlayer)) return board.toArray()

    return board.move(direction).toArray()
}

private fun moveIsIllegal(
    board: Board,
    newRowOfPlayer: Int,
    newColOfPlayer: Int
): Boolean {
    return when {
        newColOfPlayer >= board.getWidth() -> true
        newColOfPlayer < 0 -> true
        newRowOfPlayer >= board.getHeight() -> true
        newRowOfPlayer < 0 -> true
        board[newRowOfPlayer, newColOfPlayer].type == WALL -> true
        else -> false
    }
}

private fun findPlayerColumn(board: Board) =
    board.squares.single { it.type.isPlayer }.col

private fun findPlayerRow(board: Board) =
    board.squares.single { it.type.isPlayer }.row

class IllegalMoveException(override val message: String): Exception()
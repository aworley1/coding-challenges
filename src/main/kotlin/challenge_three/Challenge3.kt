package challenge_three

import challenge_three.SquareType.*

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

    operator fun get(row: Int, column: Int): Square {
        return squares.singleOrNull { it.row == row && it.col == column }
            ?: throw IllegalMoveException("Not possible to move off the board")
    }

    fun getPlayerSquare(): Square {
        return squares.single { it.type.isPlayer }
    }

    fun move(direction: Direction): Board {
        val playerStart = getPlayerSquare()
        val nextSquare = getSquareInDirection(playerStart, direction)

        val board = moveBox(nextSquare, direction)

        return board.movePlayer(playerStart, direction)
    }

    private fun movePlayer(from: Square, direction: Direction): Board {
        val to = this.getSquareInDirection(from, direction)

        return this.removeSquare(from)
            .removeSquare(to)
            .addSquare(from.removePlayer())
            .addSquare(to.addPlayer())
    }

    private fun moveBox(from: Square, direction: Direction): Board {
        if (!from.type.isBox) return this
        val to = this.getSquareInDirection(from, direction)
        return this.removeSquare(from)
            .removeSquare(to)
            .addSquare(from.removeBox())
            .addSquare(to.addBox())
    }

    private fun getSquareInDirection(
        square: Square,
        direction: Direction
    ): Square {
        return get(
            row = square.row + direction.verticalMovement,
            column = square.col + direction.horizontalMovement
        )
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
            BOX -> throw IllegalArgumentException("Cannot add a player to a box")
            STORAGE_LOCATION_WITH_BOX -> throw IllegalArgumentException("Cannot add a player to a box")
        }
    }

    fun removePlayer(): Square {
        return when (type) {
            EMPTY -> throw IllegalArgumentException("Cannot remove a player from this square")
            STORAGE_LOCATION -> throw IllegalArgumentException("Cannot remove a player from this square")
            WALL -> throw IllegalArgumentException("Cannot remove a player from this square")
            PLAYER -> this.copy(type = EMPTY)
            STORAGE_LOCATION_WITH_PLAYER -> this.copy(type = STORAGE_LOCATION)
            BOX -> throw IllegalArgumentException("Cannot remove a player from this square")
            STORAGE_LOCATION_WITH_BOX -> throw IllegalArgumentException("Cannot remove a player from this square")
        }
    }

    fun addBox(): Square {
        return when (type) {
            EMPTY -> this.copy(type = BOX)
            STORAGE_LOCATION -> this.copy(type = STORAGE_LOCATION_WITH_BOX)
            WALL -> throw IllegalMoveException("Cannot add a box to a wall")
            PLAYER -> throw IllegalArgumentException("Cannot add a box to a player")
            STORAGE_LOCATION_WITH_PLAYER -> throw IllegalArgumentException("Cannot add a box to a player")
            BOX -> throw IllegalMoveException("Cannot add a box to a box")
            STORAGE_LOCATION_WITH_BOX -> throw IllegalMoveException("Cannot add a box to a box")
        }
    }

    fun removeBox(): Square {
        return when (type) {
            BOX -> this.copy(type = EMPTY)
            STORAGE_LOCATION_WITH_BOX -> this.copy(type = STORAGE_LOCATION)
            EMPTY -> throw IllegalArgumentException("No box to remove")
            STORAGE_LOCATION -> throw IllegalArgumentException("No box to remove")
            WALL -> throw IllegalArgumentException("No box to remove")
            PLAYER -> throw IllegalArgumentException("No box to remove")
            STORAGE_LOCATION_WITH_PLAYER -> throw IllegalArgumentException("No box to remove")
        }
    }
}

enum class SquareType(val code: Char, val isPlayer: Boolean, val isBox: Boolean) {
    EMPTY(' ', false, false),
    PLAYER('p', true, false),
    WALL('#', false, false),
    STORAGE_LOCATION('*', false, false),
    STORAGE_LOCATION_WITH_PLAYER('P', true, false),
    BOX('b', false, true),
    STORAGE_LOCATION_WITH_BOX('B', false, true);

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

    return try {
        board.move(direction).toArray()
    } catch (ex: IllegalMoveException) {
        board.toArray()
    }
}

fun hasPlayerWon(board: List<String>): Boolean {
    return !board.joinToString("").contains('b')
}

class IllegalMoveException(override val message: String) : Exception()
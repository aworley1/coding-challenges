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

data class Board(val rows: List<Row>) {
    fun toArray(): List<String> {
        return rows.map {
            it.squares
                .map { it.toString() }
                .joinToString("")
        }
    }

    fun getHeight(): Int {
        return rows.size
    }

    fun getWidth(): Int {
        return rows[0].squares.size
    }

    operator fun get(row: Int, column: Int): Square {
        return rows[row].squares[column]
    }

    companion object {
        fun from(input: List<String>): Board {
            val rows = input.mapIndexed {rowIndex, row ->
                Row(row.toCharArray().mapIndexed { colIndex, square ->
                    Square(SquareType.from(square), rowIndex, colIndex)
                })
            }
            return Board(rows)
        }
    }
}

data class Row(val squares: List<Square>)

data class Square(
    val type: SquareType = SquareType.EMPTY,
    val row: Int,
    val col: Int
) {
    override fun toString(): String {
        return type.toString()
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
            return values().single<SquareType> { it.code == square }
        }
    }
}

fun processSokobanMove(input: List<String>, move: Char): List<String> {
    val board = Board.from(input)
    val direction = Direction.of(move.toUpperCase()) ?: return board.toArray()

    val playerCharacter = findPlayerSquareType(board)

    val rowOfPlayer = findPlayerRow(board)
    val colOfPlayer = findPlayerColumn(board, rowOfPlayer)

    val newColOfPlayer = colOfPlayer + direction.horizontalMovement
    val newRowOfPlayer = rowOfPlayer + direction.verticalMovement

    if (moveIsIllegal(board, newRowOfPlayer, newColOfPlayer)) return board.toArray()

    val valueOfNewPosition = board[newRowOfPlayer, newColOfPlayer].type
    val newPlayerCharacter = newPlayerCharacter(valueOfNewPosition)

    val replacementForOldPlayerCharacter = replacementForOldPlayerCharacter(playerCharacter)

    return board.toArray().map { it.replace(playerCharacter.code, replacementForOldPlayerCharacter.code) }
        .mapIndexed { index, row ->
            if (index == newRowOfPlayer) {
                row.replaceRange(
                    startIndex = newColOfPlayer,
                    endIndex = newColOfPlayer + 1,
                    replacement = newPlayerCharacter.toString()
                )
            } else {
                row
            }
        }
}

private fun replacementForOldPlayerCharacter(playerCharacter: SquareType): SquareType {
    return when (playerCharacter) {
        PLAYER -> EMPTY
        STORAGE_LOCATION_WITH_PLAYER -> STORAGE_LOCATION
        else -> throw IllegalArgumentException("Not a player character")
    }
}

private fun newPlayerCharacter(valueOfNewPosition: SquareType): SquareType {
    return when (valueOfNewPosition) {
        STORAGE_LOCATION -> STORAGE_LOCATION_WITH_PLAYER
        else -> PLAYER
    }
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

fun findPlayerSquareType(board: Board): SquareType {
    return board.rows
        .flatMap { it.squares }
        .single { it.type.isPlayer }
        .type
}

private fun findPlayerColumn(board: Board, rowWithPlayer: Int) =
    board.rows[rowWithPlayer].squares.indexOfFirst { it.type.isPlayer }

private fun findPlayerRow(board: Board) =
    board.rows.indexOfFirst { it.squares.map { it.type.isPlayer }.contains(true) }
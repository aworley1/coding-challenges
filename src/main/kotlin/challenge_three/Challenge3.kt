package challenge_three

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

    companion object {
        fun from(input: List<String>): Board {
            val rows = input.map { Row(it.toCharArray().map { Square.from(it) }) }
            return Board(rows)
        }
    }
}

data class Row(val squares: List<Square>)

data class Square(val type: SquareType = SquareType.EMPTY) {
    override fun toString(): String {
        return when (type) {
            SquareType.STORAGE_LOCATION_WITH_PLAYER -> "P"
            SquareType.STORAGE_LOCATION -> "*"
            SquareType.PLAYER -> "p"
            SquareType.WALL -> "#"
            SquareType.EMPTY -> " "
        }
    }

    companion object {
        fun from(square: Char): Square {
            val type = SquareType.values().single { it.code == square }
            return Square(type)
        }
    }
}

enum class SquareType(val code: Char) {
    EMPTY(' '),
    PLAYER('p'),
    WALL('#'),
    STORAGE_LOCATION('*'),
    STORAGE_LOCATION_WITH_PLAYER('P')
}

fun processSokobanMove(board: List<String>, move: Char): List<String> {
    val direction = Direction.of(move.toUpperCase()) ?: return board

    val playerCharacter = findPlayerCharacter(board)

    val rowOfPlayer = findPlayerRow(board, playerCharacter)
    val colOfPlayer = findPlayerColumn(board, rowOfPlayer, playerCharacter)

    val newColOfPlayer = colOfPlayer + direction.horizontalMovement
    val newRowOfPlayer = rowOfPlayer + direction.verticalMovement

    if (moveIsIllegal(board, newRowOfPlayer, newColOfPlayer)) return board

    val valueOfNewPosition = board[newRowOfPlayer][newColOfPlayer]
    val newPlayerCharacter = newPlayerCharacter(valueOfNewPosition, playerCharacter)

    val replacementForOldPlayerCharacter = replacementForOldPlayerCharacter(playerCharacter)

    return board.map { it.replace(playerCharacter, replacementForOldPlayerCharacter) }
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

private fun replacementForOldPlayerCharacter(playerCharacter: Char): Char {
    return when (playerCharacter) {
        'p' -> ' '
        'P' -> '*'
        else -> throw IllegalArgumentException("Undefined Player Character")
    }
}

private fun newPlayerCharacter(valueOfNewPosition: Char, playerCharacter: Char): Char {
    return when (valueOfNewPosition) {
        '*' -> 'P'
        else -> 'p'
    }
}

private fun moveIsIllegal(
    board: List<String>,
    newRowOfPlayer: Int,
    newColOfPlayer: Int
): Boolean {
    return when {
        newColOfPlayer >= board[0].length -> true
        newColOfPlayer < 0 -> true
        newRowOfPlayer >= board.size -> true
        newRowOfPlayer < 0 -> true
        board[newRowOfPlayer][newColOfPlayer] == '#' -> true
        else -> false
    }
}

fun findPlayerCharacter(board: List<String>): Char {
    val possiblePlayers = listOf('p', 'P')

    return board.joinToString("").first { possiblePlayers.contains(it) }
}

private fun findPlayerColumn(board: List<String>, rowWithPlayer: Int, playerCharacter: Char) =
    board[rowWithPlayer].indexOf(playerCharacter)

private fun findPlayerRow(board: List<String>, playerCharacter: Char) =
    board.indexOfFirst { it.contains(playerCharacter) }
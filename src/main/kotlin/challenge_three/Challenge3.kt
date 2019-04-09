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

fun processSokobanMove(board: List<String>, move: Char): List<String> {
    val direction = Direction.of(move.toUpperCase()) ?: return board

    val playerCharacter = findPlayerCharacter(board)

    val rowWithPlayer = findPlayerRow(board)
    val colOfPlayer = findPlayerColumn(board, rowWithPlayer)

    return board.map { it.replace(playerCharacter, ' ') }
        .mapIndexed { index, row ->
            if (index == rowWithPlayer + direction.verticalMovement) {
                row.replaceRange(
                    startIndex = colOfPlayer + direction.horizontalMovement,
                    endIndex = colOfPlayer + direction.horizontalMovement + 1,
                    replacement = playerCharacter.toString()
                )
            } else {
                row
            }
        }
}

fun findPlayerCharacter(board: List<String>): Char {
    val possiblePlayers = listOf('p')

    return board.joinToString("").first { possiblePlayers.contains(it) }
}

private fun findPlayerColumn(board: List<String>, rowWithPlayer: Int) =
    board[rowWithPlayer].indexOf('p')

private fun findPlayerRow(board: List<String>) = board.indexOfFirst { it.contains('p') }
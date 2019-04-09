package challenge_three

enum class Direction(val code: Char, val verticalMovement: Int, val horizontalMovement: Int) {
    UP('U', -1, 0),
    DOWN('D', 1, 0),
    LEFT('L', 0, -1),
    RIGHT('R', 0, 1);

    companion object {
        fun of(code: Char): Direction {
            return Direction.values().single { it.code == code }
        }
    }
}

fun processSokobanMove(board: List<String>, move: Char): List<String> {
    val direction = Direction.of(move)
    val rowWithPlayer = board.indexOfFirst { it.contains('p') }
    val colOfPlayer = board[rowWithPlayer].indexOf('p')

    return board.map { it.replace('p', ' ') }
        .mapIndexed { index, row ->
            if (index == rowWithPlayer + direction.verticalMovement) {
                row.replaceRange(
                    colOfPlayer + direction.horizontalMovement,
                    colOfPlayer + direction.horizontalMovement + 1,
                    "p"
                )
            } else {
                row
            }
        }
}
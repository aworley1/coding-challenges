package challenge_five

fun getGridStatus(board: List<String>): String {
    return when {
        board.isFull() -> "Draw"
        board.hasPiece("R") ->"Yellow plays next"
        else -> "Red plays next"
    }
}

private fun List<String>.isFull() = this.none { it.contains(".") }

private fun List<String>.hasPiece(piece: String) = this.any { it.contains(piece) }
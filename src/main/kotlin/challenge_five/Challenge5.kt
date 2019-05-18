package challenge_five

fun getGridStatus(board: List<String>): String {
    if (board.none { it.contains(".") }) return "Draw"
    return "Yellow plays next"
}
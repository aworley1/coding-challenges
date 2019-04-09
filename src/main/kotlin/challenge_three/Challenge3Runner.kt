package challenge_three

fun main() {
    val initialBoard = listOf(
        "########",
        "#  p   #",
        "#      #",
        "########"
    )

    var board = initialBoard
    while (true) {
        printBoard(board)
        board = processSokobanMove(board, readLine()?.toCharArray()?.firstOrNull() ?: ' ')
    }
}

fun printBoard(board: List<String>) {
    board.forEach { println(it) }
}
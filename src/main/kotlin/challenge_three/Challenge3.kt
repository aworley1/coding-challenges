package challenge_three

fun processSokobanMove(board: List<String>, move: Char): List<String> {
    if (move == 'U') {
        val rowWithPlayer = board.indexOfFirst { it.contains('p') }
        val colOfPlayer = board[rowWithPlayer].indexOf('p')

        return board.map { it.replace('p', ' ') }
            .mapIndexed { index, s ->
                if (index == rowWithPlayer - 1) s.replaceRange(colOfPlayer, colOfPlayer + 1, "p")
                else s
            }
    }
    if (move == 'D') {
        val rowWithPlayer = board.indexOfFirst { it.contains('p') }
        val colOfPlayer = board[rowWithPlayer].indexOf('p')

        return board.map { it.replace('p', ' ') }
            .mapIndexed { index, s ->
                if (index == rowWithPlayer + 1) s.replaceRange(colOfPlayer, colOfPlayer + 1, "p")
                else s
            }
    }
    if (move == 'L') {
        val rowWithPlayer = board.indexOfFirst { it.contains('p') }
        val colOfPlayer = board[rowWithPlayer].indexOf('p')

        return board.map { it.replace('p', ' ') }
            .mapIndexed { index, s ->
                if (index == rowWithPlayer) s.replaceRange(colOfPlayer - 1, colOfPlayer, "p")
                else s
            }
    }
    if (move == 'R') {
        val rowWithPlayer = board.indexOfFirst { it.contains('p') }
        val colOfPlayer = board[rowWithPlayer].indexOf('p')

        return board.map { it.replace('p', ' ') }
            .mapIndexed { index, s ->
                if (index == rowWithPlayer) s.replaceRange(colOfPlayer + 1, colOfPlayer + 2, "p")
                else s
            }
    }
    return board
}
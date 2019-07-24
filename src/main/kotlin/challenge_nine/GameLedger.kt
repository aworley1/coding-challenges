package challenge_nine

import challenge_nine.TransactionType.BANK_PAYS_FEE
import challenge_nine.TransactionType.STARTING_BALANCE

data class GameLedger(val transactions: MutableList<Transaction> = mutableListOf()) {
    fun addStaringBalance(player: Player, amount: Int) {
        transactions.add(
            Transaction(
                player = player,
                credit = amount,
                type = STARTING_BALANCE
            )
        )
    }

    fun payFeeToPlayer(player: Player, amount: Int) {
        transactions.add(
            Transaction(
                player = player,
                credit = amount,
                type = BANK_PAYS_FEE
            )
        )
    }
}

data class Transaction(
    val player: Player,
    val credit: Int,
    val type: TransactionType
)

enum class TransactionType {
    STARTING_BALANCE,
    BANK_PAYS_FEE
}
package challenge_nine

import challenge_nine.TransactionType.*

data class GameLedger(val transactions: MutableList<Transaction> = mutableListOf()) {
    fun addStaringBalance(player: Player, amount: Int) {
        transactions.add(
            Transaction(
                player = player,
                credit = amount,
                debit = 0,
                type = STARTING_BALANCE
            )
        )
    }

    fun payFeeToPlayer(player: Player, amount: Int) {
        transactions.add(
            Transaction(
                player = player,
                credit = amount,
                debit = 0,
                type = BANK_PAYS_FEE
            )
        )
    }

    fun payRent(from: Player, to: Player, amount: Int) {
        transactions.add(Transaction(player = from, credit = 0, debit = amount, type = PAY_RENT))
        transactions.add(Transaction(player = to, credit = amount, debit = 0, type = RECEIVE_RENT))
    }
}

data class Transaction(
    val player: Player,
    val credit: Int,
    val debit: Int,
    val type: TransactionType
)

enum class TransactionType {
    STARTING_BALANCE,
    BANK_PAYS_FEE,
    PAY_RENT,
    RECEIVE_RENT
}
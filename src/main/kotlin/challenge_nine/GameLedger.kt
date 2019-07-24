package challenge_nine

import challenge_nine.TransactionReason.*
import challenge_nine.TransactionType.CREDIT
import challenge_nine.TransactionType.DEBIT

data class GameLedger(val transactions: MutableList<Transaction> = mutableListOf()) {
    fun addStaringBalance(player: Player, amount: Int) {
        transactions.add(
            Transaction(
                player = player,
                financialAmount = FinancialAmount(amount, CREDIT),
                reason = STARTING_BALANCE
            )
        )
    }

    fun payFeeToPlayer(player: Player, amount: Int) {
        transactions.add(
            Transaction(
                player = player,
                financialAmount = FinancialAmount(amount, CREDIT),
                reason = BANK_PAYS_FEE
            )
        )
    }

    fun payRent(from: Player, to: Player, amount: Int) {
        transactions.add(
            Transaction(
                player = from,
                financialAmount = FinancialAmount(amount, DEBIT),
                reason = PAY_RENT
            )
        )
        transactions.add(
            Transaction(
                player = to,
                financialAmount = FinancialAmount(amount, CREDIT),
                reason = RECEIVE_RENT
            )
        )
    }
}

data class Transaction(
    val player: Player,
    val financialAmount: FinancialAmount,
    val reason: TransactionReason
)

data class FinancialAmount(
    val amount: Int,
    val type: TransactionType
)

enum class TransactionReason {
    STARTING_BALANCE,
    BANK_PAYS_FEE,
    PAY_RENT,
    RECEIVE_RENT

}

enum class TransactionType {
    CREDIT,
    DEBIT
}
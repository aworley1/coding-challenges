package challenge_nine

data class GameLedger(val transactions: MutableList<Transaction> = mutableListOf()) {
    fun addStaringBalance(amount: Int) {
        transactions.add(Transaction(credit = amount))
    }
}

data class Transaction(val credit: Int)
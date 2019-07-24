package challenge_nine

import assertk.assertThat
import assertk.assertions.isEqualTo
import challenge_nine.TransactionType.STARTING_BALANCE
import org.junit.jupiter.api.Test

internal class GameLedgerTest {
    @Test
    fun `should add starting balance for player`() {
        val ledger = GameLedger()
        val player = Player(1)

        ledger.addStaringBalance(player, 10)

        val addedTransaction = ledger.transactions.single()

        assertThat(addedTransaction.credit).isEqualTo(10)
        assertThat(addedTransaction.type).isEqualTo(STARTING_BALANCE)
        assertThat(addedTransaction.player).isEqualTo(player)
    }
}
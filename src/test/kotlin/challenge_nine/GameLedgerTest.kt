package challenge_nine

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class GameLedgerTest {
    @Test
    fun `should add starting balance`() {
        val ledger = GameLedger()

        ledger.addStaringBalance(10)

        val addedTransaction = ledger.transactions.single()

        assertThat(addedTransaction.credit).isEqualTo(10)
    }
}
package challenge_nine

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import challenge_nine.TransactionReason.*
import challenge_nine.TransactionType.*
import org.junit.jupiter.api.Test

internal class GameLedgerTest {
    @Test
    fun `should add starting balance for player`() {
        val ledger = GameLedger()
        val player = Player(1)

        ledger.addStaringBalance(player, 10)

        val addedTransaction = ledger.transactions.single()

        assertThat(addedTransaction.financialAmount.amount).isEqualTo(10)
        assertThat(addedTransaction.financialAmount.type).isEqualTo(CREDIT)
        assertThat(addedTransaction.reason).isEqualTo(STARTING_BALANCE)
        assertThat(addedTransaction.player).isEqualTo(player)
    }

    @Test
    fun `should pay fee to player`() {
        val ledger = GameLedger()
        val player = Player(1)

        ledger.payFeeToPlayer(player, 12)

        val addedTransaction = ledger.transactions.single()

        assertThat(addedTransaction.financialAmount.amount).isEqualTo(12)
        assertThat(addedTransaction.financialAmount.type).isEqualTo(CREDIT)
        assertThat(addedTransaction.reason).isEqualTo(BANK_PAYS_FEE)
        assertThat(addedTransaction.player).isEqualTo(player)
    }

    @Test
    fun `should pay rent from one player to another`() {
        val ledger = GameLedger()
        val playerPayingRent = Player(1)
        val playerBeingPaidRent = Player(2)

        ledger.payRent(playerPayingRent, playerBeingPaidRent, 17)

        assertThat(ledger.transactions).hasSize(2)
        assertThat(ledger.transactions[0].player).isEqualTo(playerPayingRent)
        assertThat(ledger.transactions[0].reason).isEqualTo(PAY_RENT)
        assertThat(ledger.transactions[0].financialAmount.amount).isEqualTo(17)
        assertThat(ledger.transactions[0].financialAmount.type).isEqualTo(DEBIT)

        assertThat(ledger.transactions[1].player).isEqualTo(playerBeingPaidRent)
        assertThat(ledger.transactions[1].reason).isEqualTo(RECEIVE_RENT)
        assertThat(ledger.transactions[1].financialAmount.amount).isEqualTo(17)
        assertThat(ledger.transactions[1].financialAmount.type).isEqualTo(CREDIT)

    }

    @Test
    fun `should add transaction to purchase location`() {
        val ledger = GameLedger()
        val player = Player(1)

        ledger.purchaseLocation(player)

        assertThat(ledger.transactions[0].player).isEqualTo(player)
        assertThat(ledger.transactions[0].reason).isEqualTo(PURCHASE_LOCATION)
    }
}
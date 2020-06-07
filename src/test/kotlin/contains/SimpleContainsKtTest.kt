package contains

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class SimpleContainsKtTest {
    @Test
    fun `should find element`() {
        val list = listOf(1, 10, 5)

        assertThat(list.simpleContains(5)).isTrue()
    }

    @Test
    fun `should not find element if not present`() {
        val list = listOf(1, 10, 5)

        assertThat(list.simpleContains(3)).isFalse()
    }

    @Test
    fun `should handle empty list`() {
        val list = listOf<Int>()

        assertThat(list.simpleContains("3")).isFalse()
    }
}
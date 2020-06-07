package contains

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BinaryChopKtTest {
    @Test
    fun `should find element on left side`() {
        val list = listOf(1, 5, 8, 10, 16, 100, 900, 1024)

        assertThat(list.binaryContains(5)).isTrue()
    }

    @Test
    fun `should find element on right side`() {
        val list = listOf(1, 5, 8, 10, 16, 100, 900, 1024)

        assertThat(list.binaryContains(1024)).isTrue()
    }

    @Test
    fun `should not find element if not present`() {
        val list = listOf(1, 5, 8, 10, 16)

        assertThat(list.binaryContains(3)).isFalse()
    }

    @Test
    fun `should handle empty list`() {
        val list = listOf<Int>()

        assertThat(list.binaryContains(3)).isFalse()
    }
}
package challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Challenge2KtTest {
    @Test
    fun `should return valid label unmodified for was, then, then, now`() {
        val originalLabel = "Was £10, then £9, then £8, now £6"

        assertEquals(originalLabel, fixPriceLabel(originalLabel))
    }

    @Test
    fun `should return valid label unmodified for was, then, now`() {
        val originalLabel = "Was £10, then £8, now £6"

        assertEquals(originalLabel, fixPriceLabel(originalLabel))
    }

    @Test
    fun `should return valid label unmodified for was, now`() {
        val originalLabel = "Was £10, now £6"

        assertEquals(originalLabel, fixPriceLabel(originalLabel))
    }

    @Test
    fun `should return valid label unmodified for now`() {
        val originalLabel = "now £6"

        assertEquals(originalLabel, fixPriceLabel(originalLabel))
    }

    @Test
    fun `should fix label for was now`() {
        val originalLabel = "Was £4, now £6"

        assertEquals("now £6", fixPriceLabel(originalLabel))
    }
}
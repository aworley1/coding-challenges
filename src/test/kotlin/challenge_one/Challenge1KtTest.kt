package challenge_one

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Challenge1KtTest {
    @Test
    fun `should return none`() {
        assertEquals("none", formatTime(0))
    }

    @Test
    fun `should return 1 second`() {
        assertEquals("1 second", formatTime(1))
    }

    @Test
    fun `should return 2 seconds`() {
        assertEquals("2 seconds", formatTime(2))
    }

    @Test
    fun `should return 1 minute`() {
        assertEquals("1 minute", formatTime(60))
    }

    @Test
    fun `should return 1 minute, 30 seconds`() {
        assertEquals("1 minute, 30 seconds", formatTime(90))
    }

    @Test
    fun `should return 1 hour, 1 second`() {
        assertEquals("1 hour, 1 second", formatTime(3601))
    }

    @Test
    fun `should return 1 year, 3 days, 2 seconds`() {
        assertEquals("1 year, 3 days, 2 seconds", formatTime(31795202))
    }
}
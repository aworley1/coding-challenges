package challenge_three

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Challenge3KtTest {
    @Test
    fun `should return a board of the same dimensions as input`() {
        val input = listOf(
            "########",
            "########",
            "###p####",
            "########"
        )

        val result = processSokobanMove(input, 'U')

        assertEquals(4, result.size)
        assertEquals(8, result[0].length)
    }

    @Test
    fun `should return original board if move is not a valid character`() {
        val input = listOf(
            "########",
            "########",
            "###p####",
            "########"
        )

        val result = processSokobanMove(input, 'Z')

        assertEquals(input, result)
    }

    @Test
    fun `should move one square up when there is space`() {
        val input = listOf(
            "########",
            "#      #",
            "#  p   #",
            "########"
        )

        val result = processSokobanMove(input, 'U')

        val expectedResult = listOf(
            "########",
            "#  p   #",
            "#      #",
            "########"
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun `should move one square down when there is space`() {
        val input = listOf(
            "########",
            "#  p   #",
            "#      #",
            "########"
        )

        val result = processSokobanMove(input, 'D')

        val expectedResult = listOf(
            "########",
            "#      #",
            "#  p   #",
            "########"
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun `should move one square left when there is space`() {
        val input = listOf(
            "########",
            "#  p   #",
            "#      #",
            "########"
        )

        val result = processSokobanMove(input, 'L')

        val expectedResult = listOf(
            "########",
            "# p    #",
            "#      #",
            "########"
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun `should move one square right when there is space`() {
        val input = listOf(
            "########",
            "#  p   #",
            "#      #",
            "########"
        )

        val result = processSokobanMove(input, 'R')

        val expectedResult = listOf(
            "########",
            "#   p  #",
            "#      #",
            "########"
        )

        assertEquals(expectedResult, result)
    }


}
package challenges

import java.math.BigDecimal

fun fixPriceLabel(original: String): String {
    val regex = "(?<=£)(.*?)(?=,|\$)".toRegex()

    val prices = regex.findAll(original).map { it.value.toBigDecimal() }.toList()

    val sortedPrices = prices.sortedDescending().toList()

    if (prices == sortedPrices) return original

    return buildOutput(prices)
}

fun buildOutput(amounts: List<BigDecimal>): String {
    return "now £${amounts.last().toString()}"
}
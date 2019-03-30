package challenges

fun fixPriceLabel(original: String): String {
    val regex = "(?<=£)(.*?)(?=,|\$)".toRegex()

    val prices = regex.findAll(original).map { it.value.toBigDecimal() }.toList()

    val sortedPrices = prices.sortedDescending().toList()

    if (prices == sortedPrices) return original

    return "needs to be fixed"
}
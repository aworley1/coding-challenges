package challenge_one

enum class TimeUnit(val seconds: Int, val singular: String, val plural: String) {
    YEAR(31536000, "year", "years"),
    DAY(86400, "day", "days"),
    HOUR(3600, "hour", "hours"),
    MINUTE(60, "minute", "minutes"),
    SECOND(1, "second", "seconds");

    fun unitName(seconds: Int): String {
        return if (this.seconds % seconds == 0) singular else plural
    }

    fun format(seconds: Int): String? {
        if (seconds / this.seconds == 0) return null
        return "${seconds / this.seconds} ${this.unitName(seconds)}"
    }
}

fun formatTime(seconds: Int): String {
    return listOf(TimeUnit.MINUTE, TimeUnit.SECOND)
        .mapNotNull { it.format(seconds) }
        .joinToString(" ")

}
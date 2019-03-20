package challenge_one

enum class TimeUnit(val seconds: Int, val singular: String, val plural: String) {
    YEAR(31536000, "year", "years"),
    DAY(86400, "day", "days"),
    HOUR(3600, "hour", "hours"),
    MINUTE(60, "minute", "minutes"),
    SECOND(1, "second", "seconds");

    fun howMany(seconds: Int): Int {
        return seconds / this.seconds
    }
}

fun formatTime(seconds: Int): String {
    if (seconds == 0) return "none"

    val unitsAndRemainingTime = UnitsAndRemainingTime(seconds, emptyList())

    return TimeUnit.values()
        .fold(unitsAndRemainingTime, ::moveRemainingTimeIntoUnit)
        .unitsAndNumberOfThem
        .mapNotNull { it.formatted }
        .joinWithCommaThenAnd()
}

private fun moveRemainingTimeIntoUnit(
    currentUnitsAndRemainingTime: UnitsAndRemainingTime,
    timeUnit: TimeUnit
): UnitsAndRemainingTime {
    val timeUnitAndNumberOfThem = TimeUnitAndNumberOfThem(
        timeUnit = timeUnit,
        numberOfThem = timeUnit.howMany(currentUnitsAndRemainingTime.remainingSeconds)
    )

    val remainingSeconds =
        currentUnitsAndRemainingTime.remainingSeconds - timeUnitAndNumberOfThem.totalSeconds

    return UnitsAndRemainingTime(
        remainingSeconds = remainingSeconds,
        unitsAndNumberOfThem = currentUnitsAndRemainingTime.unitsAndNumberOfThem + timeUnitAndNumberOfThem
    )
}

data class UnitsAndRemainingTime(val remainingSeconds: Int, val unitsAndNumberOfThem: List<TimeUnitAndNumberOfThem>)

data class TimeUnitAndNumberOfThem(val timeUnit: TimeUnit, val numberOfThem: Int) {
    val formatted: String?
        get() {
            return when {
                numberOfThem == 0 -> null
                numberOfThem == 1 -> "$numberOfThem ${timeUnit.singular}"
                else -> "$numberOfThem ${timeUnit.plural}"
            }
        }

    val totalSeconds: Int
        get() = numberOfThem * timeUnit.seconds
}

fun List<String>.joinWithCommaThenAnd(): String {
    return this.mapIndexed { index, s ->
        when {
            index == this.size - 1 -> listOf(s)
            index == this.size - 2 -> listOf(s, " and ")
            else -> listOf(s, ", ")
        }
    }
        .flatten()
        .joinToString("")
}
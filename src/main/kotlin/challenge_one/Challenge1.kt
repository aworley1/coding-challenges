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
    val timeAccumulator = AccumulatedTimeInUnits(seconds, emptyList())
    return TimeUnit.values()
        .fold(timeAccumulator, accumulateRemainingTime())
        .unitsAndNumberOfThem
        .mapNotNull { it.formatted }
        .joinToString(" ")
}

private fun accumulateRemainingTime(): (AccumulatedTimeInUnits, TimeUnit) -> AccumulatedTimeInUnits {
    return fun(acc: AccumulatedTimeInUnits, timeUnit: TimeUnit): AccumulatedTimeInUnits {
        val timeUnitAndNumberOfThem = TimeUnitAndNumberOfThem(timeUnit, timeUnit.howMany(acc.remainingSeconds))
        val remainingSeconds =
            acc.remainingSeconds - (timeUnitAndNumberOfThem.numberOfThem * timeUnitAndNumberOfThem.timeUnit.seconds)

        return AccumulatedTimeInUnits(
            remainingSeconds = remainingSeconds,
            unitsAndNumberOfThem = acc.unitsAndNumberOfThem + timeUnitAndNumberOfThem
        )
    }
}

data class AccumulatedTimeInUnits(val remainingSeconds: Int, val unitsAndNumberOfThem: List<TimeUnitAndNumberOfThem>)

data class TimeUnitAndNumberOfThem(val timeUnit: TimeUnit, val numberOfThem: Int) {
    val formatted: String?
        get() {
            if (numberOfThem == 0) return null
            return if (numberOfThem == 1) "$numberOfThem ${timeUnit.singular}"
            else "$numberOfThem ${timeUnit.plural}"
        }
}
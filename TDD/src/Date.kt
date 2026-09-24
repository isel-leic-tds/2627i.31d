private const val GREGORIAN_START = 1582
private const val MAX_YEAR = 2200

class Date(val year: Int, val month: Int = 1, val day: Int = 1) {
    init {
        require(year in GREGORIAN_START..MAX_YEAR) { "Invalid year" }
        require(month in 1..daysOfMonths.size) { "Invalid month" }
        require(day in 1..lastDayOfMonth) { "Invalid day" }
    }
    override fun equals(other: Any?): Boolean =
        other is Date && year==other.year && month==other.month && day==other.day

    override fun hashCode(): Int =
        year shl 9 or month shl 5 or day
}

val Int.isLeapYear: Boolean
    get() = this % 4 == 0 && (this % 100 != 0 || this % 400 == 0)

val Date.hasLeapYear: Boolean
    get() = year.isLeapYear

private val daysOfMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

val Date.lastDayOfMonth: Int
    get() = if (month == 2 && hasLeapYear) 29 else daysOfMonths[month - 1]

/**
 * Add days to a date.
 * @param days number of days to add
 * @return new date
 */
tailrec fun Date.addDays(days: Int): Date {
    require(days > 0) { "days must be positive" }
    return when {
        day + days <= lastDayOfMonth ->
            Date(year, month, day + days)
        month < 12 ->
            Date(year, month + 1, 1).addDays(days - (lastDayOfMonth - day + 1))
        else ->
            Date(year + 1, 1, 1).addDays(days - (lastDayOfMonth - day + 1))
    }
}

operator fun Date.plus(days: Int): Date = this.addDays(days)
operator fun Int.plus(date: Date): Date = date.addDays(this)



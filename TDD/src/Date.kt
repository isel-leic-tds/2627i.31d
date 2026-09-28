private const val GREGORIAN_START = 1582
private const val MAX_YEAR = 2200

private const val YEAR_BITS = 12
private const val MONTH_BITS = 4
private const val DAY_BITS = 5

value class Date private constructor(private val bits: Int) {
    constructor(year: Int, month: Int = 1, day: Int = 1)
      : this((year shl (MONTH_BITS+DAY_BITS)) or (month shl DAY_BITS) or day)
    init {
        require(year in GREGORIAN_START..MAX_YEAR) { "Invalid year" }
        require(month in 1..daysOfMonths.size) { "Invalid month" }
        require(day in 1..lastDayOfMonth) { "Invalid day" }
    }

    val year: Int get() = bits shr (MONTH_BITS+DAY_BITS)
    val month: Int get() = (bits shr DAY_BITS) and ((1 shl MONTH_BITS) - 1)
    val day: Int get() = bits and ((1 shl DAY_BITS) - 1)

    override fun toString(): String =
        "%04d-%02d-%02d".format(year,month, day)

    operator fun compareTo(dt: Date): Int = bits - dt.bits
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
 * @return new date after adding days
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


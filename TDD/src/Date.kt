

class Date(val year: Int, val month: Int = 1, val day: Int = 1)

val Int.isLeapYear: Boolean
    get() = this % 4 == 0 && (this % 100 != 0 || this % 400 == 0)

val Date.hasLeapYear: Boolean
    get() = year.isLeapYear

private val daysOfMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

val Date.lastDayOfMonth: Int
    get() = if (month == 2 && hasLeapYear) 29 else daysOfMonths[month - 1]


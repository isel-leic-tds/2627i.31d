import kotlin.test.*

class DateTests {
    @Test fun `Get the last day of the month`() {
        val sut = Date(2024, 2)
        assertEquals(29,sut.lastDayOfMonth) { "Must be 29" }
        assertEquals(31,Date(2026,12).lastDayOfMonth)
        assertEquals(30,Date(2026,9).lastDayOfMonth)
    }
    @Test fun `Check if data has a leap year`() {
        val sut = Date(2024, day = 2)
        assertTrue(sut.hasLeapYear) { "2024 is a leap year" }
        val sut2 = Date(2026)
        assertFalse(sut2.hasLeapYear) { "2026 is not a leap year" }
    }
    @Test fun `Create Date only with year and month`() {
        val sut = Date(2026, 9)
        assertEquals(2026, sut.year)
        assertEquals(1, sut.day) { "Day must be 1" }
    }
    @Test fun `Create Date only with year`() {
        val sut = Date(2026)
        assertEquals(2026, sut.year)
        assertEquals(1, sut.day) { "Day must be 1" }
        assertEquals(1, sut.month) { "Month must be 1" }
    }
    @Test fun testDateCreation() {
        val sut = Date(2026, 9, 21)
        assertEquals(2026, sut.year) { "Wrong year $sut.year" }
        assertEquals(9, sut.month, "Wrong month")
        assertEquals(21, sut.day)
    }
}
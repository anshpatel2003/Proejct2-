package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class DateTest {

    private Date validDate;
    private Date invalidDate;
    private Date leapYearDate;
    private Date nonLeapYearDate;
    private Date invalidDayInMonth;
    private Date invalidMonth;

    @Before
    public void setUp() {
        validDate = new Date(2024, 6, 15);  // June 15, 2024 (valid date)
        invalidDate = new Date(2023, 2, 29);  // February 29, 2023 (not a leap year)
        leapYearDate = new Date(2024, 2, 29);  // February 29, 2024 (valid leap year date)
        nonLeapYearDate = new Date(2023, 2, 28);  // February 28, 2023 (valid non-leap year date)
        invalidDayInMonth = new Date(2024, 4, 31);  // April 31, 2024 (April has 30 days)
        invalidMonth = new Date(2024, 13, 10);
    }

    @Test
    public void testValidDate() {
        assertTrue(validDate.isValid());  // June 15, 2024 is a valid date
    }

    @Test
    public void testInvalidDate() {
        assertFalse(invalidDate.isValid());  // February 29, 2023 is not a valid date (not a leap year)
    }

    @Test
    public void testLeapYearDate() {
        assertTrue(leapYearDate.isValid());  // February 29, 2024 is a valid date (leap year)
    }

    @Test
    public void testNonLeapYearDate() {
        assertTrue(nonLeapYearDate.isValid());  // February 28, 2023 is a valid date
    }

    @Test
    public void testInvalidDayInMonth() {
        assertFalse(invalidDayInMonth.isValid());  // April 31, 2024 is not valid (April has 30 days)
    }

    @Test
    public void testInvalidMonth() {
        assertFalse(invalidMonth.isValid());  // Month 13 is not valid
    }
}
/**
 * @author Jeet Soni, Ansh Patel
 */

package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Date class, focusing on validating dates, including leap year scenarios,
 * invalid months, and invalid days in certain months.
 */
public class DateTest {

    private Date validLeapYearDate;
    private Date validNonLeapYearDate;
    private Date invalidLeapYearDate;
    private Date invalidMonth;
    private Date invalidDayInMonth;
    private Date invalidDateInLeapYear;

    /**
     * Sets up the test cases for different date scenarios including valid and invalid leap years,
     * invalid months, and invalid day combinations.
     */
    @Before
    public void setUp() {
        validLeapYearDate = new Date(2024, 2, 29);  // February 29, 2024 (valid leap year date)
        validNonLeapYearDate = new Date(2023, 12, 31);  // December 31, 2023 (valid date)

        invalidLeapYearDate = new Date(2023, 2, 29);  // February 29, 2023 (not a leap year)
        invalidMonth = new Date(2023, 13, 1);  // Invalid month (13)
        invalidDayInMonth = new Date(2023, 4, 31);  // April 31, 2023 (invalid, April has 30 days)
        invalidDateInLeapYear = new Date(2020, 2, 30);  // February 30, 2020 (invalid leap year date)
    }

    /**
     * Test that a valid leap year date (February 29, 2024) is recognized as valid.
     */
    @Test
    public void testValidLeapYearDate() {
        assertTrue(validLeapYearDate.isValid());  // February 29, 2024 is valid (leap year)
    }

    /**
     * Test that a valid non-leap year date (December 31, 2023) is recognized as valid.
     */
    @Test
    public void testValidNonLeapYearDate() {
        assertTrue(validNonLeapYearDate.isValid());  // December 31, 2023 is valid
    }

    /**
     * Test that an invalid leap year date (February 29, 2023, not a leap year) is recognized as invalid.
     */
    @Test
    public void testInvalidLeapYearDate() {
        assertFalse(invalidLeapYearDate.isValid());  // February 29, 2023 is invalid (not a leap year)
    }

    /**
     * Test that a date with an invalid month (month 13) is recognized as invalid.
     */
    @Test
    public void testInvalidMonth() {
        assertFalse(invalidMonth.isValid());  // Month 13 is invalid
    }

    /**
     * Test that a date with an invalid day in the month (April 31, 2023) is recognized as invalid.
     */
    @Test
    public void testInvalidDayInMonth() {
        assertFalse(invalidDayInMonth.isValid());  // April 31, 2023 is invalid (April has 30 days)
    }

    /**
     * Test that an invalid leap year date (February 30, 2020, leap year but February has only 29 days) is recognized as invalid.
     */
    @Test
    public void testInvalidDateInLeapYear() {
        assertFalse(invalidDateInLeapYear.isValid());  // February 30, 2020 is invalid (leap year but Feb has 29 days)
    }
}

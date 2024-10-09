package util;

import java.util.Calendar;

/**
 * Represents a custom date with year, month, and day.
 * Provides functionality to check for leap years, validity, and date comparisons.
 * @author Jeet Soni, Ansh Patel
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    // Constants for months and their respective days
    private static final int JANUARY = 1, FEBRUARY = 2, MARCH = 3, APRIL = 4, MAY = 5, JUNE = 6;
    private static final int JULY = 7, AUGUST = 8, SEPTEMBER = 9, OCTOBER = 10, NOVEMBER = 11, DECEMBER = 12;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    private static final int[] DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Constructs a Date object with the specified year, month, and day.
     *
     * @param year  The year of the date.
     * @param month The month of the date.
     * @param day   The day of the date.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Compares this Date to another Date.
     *
     * @param other The other Date to compare to.
     * @return A negative integer, zero, or a positive integer as this Date is earlier than,
     *         equal to, or later than the specified Date.
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
        return this.day - other.day;
    }

    /**
     * Checks if this Date is equal to another object.
     * Two Dates are considered equal if they have the same year, month, and day.
     *
     * @param obj The object to compare to.
     * @return True if the Dates are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date date = (Date) obj;
        return year == date.year && month == date.month && day == date.day;
    }

    /**
     * Returns a string representation of the Date in the format MM/DD/YYYY.
     *
     * @return A formatted string representing the Date.
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    /**
     * Returns the year of this Date.
     *
     * @return The year of the Date.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the day of this Date.
     *
     * @return The day of the Date.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the month of this Date.
     *
     * @return The month of the Date.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Checks if this Date is valid (i.e., exists on the calendar).
     *
     * @return True if the Date is valid, false otherwise.
     */
    public boolean isValid() {
        if (month < JANUARY || month > DECEMBER) {
            return false;
        }
        int maxDays = DAYS_IN_MONTH[month];
        if (month == FEBRUARY && isLeapYear(year)) {
            maxDays = 29;
        }
        return day > 0 && day <= maxDays;
    }

    /**
     * Checks if a given year is a leap year.
     *
     * @param year The year to check.
     * @return True if the year is a leap year, false otherwise.
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL != 0) return false;
        if (year % CENTENNIAL != 0) return true;
        return year % QUATERCENTENNIAL == 0;
    }

    /**
     * Checks if this Date falls on a weekend (Saturday or Sunday).
     *
     * @return True if the Date is a weekend, false otherwise.
     */
    public boolean isWeekend() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    /**
     * Checks if this Date is within 6 months from today.
     *
     * @return True if the Date is within 6 months from today, false otherwise.
     */
    public boolean SixMonths() {
        Calendar today = Calendar.getInstance();
        Calendar inputDate = Calendar.getInstance();
        inputDate.set(year, month - 1, day);
        Calendar sixMonthsFromToday = (Calendar) today.clone();
        sixMonthsFromToday.add(Calendar.MONTH, 6);
        return (inputDate.after(today) && inputDate.before(sixMonthsFromToday)) || inputDate.equals(today);
    }

    /**
     * Checks if this Date is today or in the past.
     *
     * @return True if the Date is today or in the past, false otherwise.
     */
    public boolean isTodayOrPast() {
        Calendar today = Calendar.getInstance();
        Calendar inputDate = Calendar.getInstance();
        inputDate.set(year, month - 1, day);
        return inputDate.before(today) || inputDate.equals(today);
    }
}


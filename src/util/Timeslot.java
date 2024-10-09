package util;

/**
 * Represents a specific timeslot for appointments in the clinic system.
 * The timeslot is represented by an hour and minute (e.g., 9:00 AM).
 * This class implements Comparable to allow comparison between timeslots.
 * @author Your Name
 */
public class Timeslot implements Comparable<Timeslot> {

    private int hour;     // Hour of the timeslot (0-23)
    private int minute;   // Minute of the timeslot (0-59)

    /**
     * Constructs a Timeslot with the specified hour and minute.
     *
     * @param hour   The hour of the timeslot (0-23).
     * @param minute The minute of the timeslot (0-59).
     */
    public Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Retrieves the hour of the timeslot.
     *
     * @return The hour of the timeslot in 24-hour format.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Retrieves the minute of the timeslot.
     *
     * @return The minute of the timeslot.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Formats the time of the timeslot as a string in "HH:MM AM/PM" format.
     *
     * @return The formatted time as a string.
     */
    public String formatTime() {
        int hourFormatted = this.hour;
        String amPm = (hourFormatted < 12) ? "AM" : "PM";

        // Convert 24-hour time to 12-hour time
        hourFormatted = (hourFormatted == 0 || hourFormatted == 12) ? 12 : hourFormatted % 12;

        // Format time as HH:MM AM/PM
        return String.format("%d:%02d %s", hourFormatted, minute, amPm);
    }

    /**
     * Compares this timeslot with another timeslot for ordering.
     * First compares the hours, then the minutes if the hours are equal.
     *
     * @param other The other timeslot to compare with.
     * @return A negative integer, zero, or a positive integer as this timeslot
     *         is earlier than, equal to, or later than the specified timeslot.
     */
    @Override
    public int compareTo(Timeslot other) {
        if (this.hour != other.hour) {
            return Integer.compare(this.hour, other.hour);
        }
        return Integer.compare(this.minute, other.minute);
    }

    /**
     * Checks if two timeslots are equal based on their hour and minute.
     *
     * @param obj The object to compare with.
     * @return True if the timeslots are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Timeslot other = (Timeslot) obj;
        return this.hour == other.hour && this.minute == other.minute;
    }

    /**
     * Returns a string representation of the timeslot in "HH:MM" format (24-hour clock).
     *
     * @return A string representing the timeslot.
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}

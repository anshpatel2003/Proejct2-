package model;
import util.Date;
import util.Timeslot;

/**
 * Represents an appointment in the clinic system. Each appointment contains
 * a date, timeslot, patient, and provider, where patient and provider are of type Person.
 * @author Your Name
 */
public class Appointment implements Comparable<Appointment> {

    protected Date date;
    protected Timeslot timeslot;
    protected Person patient;   // Changed from Profile to Person
    protected Person provider;  // Changed from Provider to Person

    /**
     * Constructs an Appointment with the specified date, timeslot, patient, and provider.
     *
     * @param date      The date of the appointment.
     * @param timeslot  The timeslot of the appointment.
     * @param patient   The patient (of type Person) attending the appointment.
     * @param provider  The provider (of type Person) attending the appointment.
     */
    public Appointment(Date date, Timeslot timeslot, Person patient, Person provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Checks if two appointments are equal based on date, timeslot, and patient.
     *
     * @param obj The object to compare with.
     * @return True if the appointments are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment other = (Appointment) obj;
        return date.equals(other.date) &&
                timeslot.equals(other.timeslot) &&
                patient.equals(other.patient);
    }

    /**
     * Compares this appointment to another appointment for ordering.
     * First, it compares the dates, then it compares the timeslots.
     *
     * @param other The other appointment to compare with.
     * @return A negative integer, zero, or a positive integer as this appointment
     *         is less than, equal to, or greater than the specified appointment.
     */
    @Override
    public int compareTo(Appointment other) {
        if (!this.date.equals(other.date)) {
            return this.date.compareTo(other.date);
        }
        return this.timeslot.getHour() - other.timeslot.getHour();
    }

    /**
     * Returns the provider of the appointment.
     *
     * @return The provider of the appointment.
     */
    public Person getProvider() {
        return provider;
    }

    /**
     * Returns the patient of the appointment.
     *
     * @return The patient of the appointment.
     */
    public Person getPatient() {
        return patient;
    }

    /**
     * Returns the date of the appointment.
     *
     * @return The date of the appointment.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the timeslot of the appointment.
     *
     * @return The timeslot of the appointment.
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Returns a string representation of the appointment, including the date,
     * time, patient information, provider information, and location details.
     *
     * @return A formatted string representing the appointment.
     */
    @Override
    public String toString() {
        //2/28/2025 9:00 AM John Doe 12/13/1989 [ANDREW PATEL 1/21/1989, BRIDGEWATER, Somerset 08807][FAMILY, #01] booked.
        return String.format("%s %s %s %s",
                date.toString(),
                formatTime(),
                patient.toString(),
                provider.toString()


        );
    }

  
    /**
     * Helper method to format the time in 12-hour format with AM/PM notation.
     *
     * @return The formatted time as a string (e.g., "02:30 PM").
     */
    private String formatTime() {
        int hour = timeslot.getHour();
        int minute = timeslot.getMinute();
        String amPm = (hour < 12) ? "AM" : "PM";

        // Convert 24-hour time to 12-hour time
        hour = (hour == 0 || hour == 12) ? 12 : hour % 12;

        // Format time as HH:MM AM/PM
        return String.format("%d:%02d %s", hour, minute, amPm);
    }

}

package model;
import util.Radiology;
import util.Timeslot;
import util.Date;
/**
 * Represents an Imaging appointment in the clinic system.
 * This class extends the Appointment class to include an imaging room (e.g., X-ray, Ultrasound, CAT scan).
 * The room is represented by the Radiology enum.
 * @author Your Name
 */
public class Imaging extends Appointment {

    private Radiology room;  // The type of imaging room (e.g., XRAY, ULTRASOUND, CATSCAN)

    /**
     * Constructs an Imaging appointment with the specified date, timeslot, patient, provider, and room.
     *
     * @param date      The date of the imaging appointment.
     * @param timeslot  The timeslot of the imaging appointment.
     * @param patient   The patient attending the appointment.
     * @param provider  The provider attending the appointment (e.g., a technician).
     * @param room      The radiology room for the imaging appointment (e.g., XRAY, ULTRASOUND, CATSCAN).
     */
    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room) {
        super(date, timeslot, patient, provider);  // Call the constructor of the Appointment class
        this.room = room;
    }

    /**
     * Returns the radiology room for the imaging appointment.
     *
     * @return The type of imaging room (XRAY, ULTRASOUND, CATSCAN).
     */
    public Radiology getRoom() {
        return room;
    }

    /**
     * Sets the radiology room for the imaging appointment.
     *
     * @param room The new radiology room for the appointment.
     */
    public void setRoom(Radiology room) {
        this.room = room;
    }

    /**
     * Returns the provider attending the imaging appointment.
     *
     * @return The provider attending the appointment.
     */
    public Person getProvider() {
        return super.getProvider();
    }

    /**
     * Returns a string representation of the imaging appointment, including the room type.
     *
     * @return A string representing the imaging appointment details.
     */
    @Override
    public String toString() {
        return super.toString() + " Room: " + room.name();
    }
}

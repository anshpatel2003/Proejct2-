package model;

/**
 * Represents a Technician in the clinic system.
 * A technician has a rate per visit, which defines how much they charge for each imaging appointment.
 * Extends the Provider class to inherit profile and location information.
 * @author Your Name
 */
public class Technician extends Provider {

    private int ratePerVisit;  // The charging rate per visit for the technician

    /**
     * Constructs a Technician object with the specified profile, location, and rate per visit.
     *
     * @param profile      The profile of the technician (inherited from Person).
     * @param location     The location of the technician's practice (inherited from Provider).
     * @param ratePerVisit The charging rate per visit for the technician.
     */
    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);  // Call the constructor of the Provider class
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Returns the rate per visit for the technician.
     *
     * @return The technician's rate per visit.
     */
    @Override
    public int rate() {
        return ratePerVisit;
    }

    /**
     * Returns the rate per visit for the technician.
     *
     * @return The rate per visit.
     */
    public int getRatePerVisit() {
        return ratePerVisit;
    }

    /**
     * Sets a new rate per visit for the technician.
     *
     * @param ratePerVisit The new rate per visit for the technician.
     */
    public void setRatePerVisit(int ratePerVisit) {
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Returns a string representation of the technician, including the profile, location, and rate per visit.
     *
     * @return A string representing the technician's profile, location, and rate per visit.
     */
    @Override
    public String toString() {
        return super.toString() + " [rate: " + "$" +ratePerVisit +".00]";
    }
}

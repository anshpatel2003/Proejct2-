package model;

/**
 * Represents a Doctor in the clinic system.
 * A doctor has a specialty and a unique National Provider Identification (NPI).
 * The charge per visit is based on the doctor's specialty.
 * Extends the Provider class to inherit profile and location information.
 * @author Ansh Patel, Jeet Soni
 */
public class Doctor extends Provider {

    private Specialty specialty;  // The specialty of the doctor (e.g., FAMILY, PEDIATRICIAN)
    private String npi;           // National Provider Identification (unique to each doctor)

    /**
     * Constructs a Doctor object with the specified profile, location, specialty, and NPI.
     *
     * @param profile   The profile of the doctor (inherited from Person).
     * @param location  The location of the doctor's practice (inherited from Provider).
     * @param specialty The specialty of the doctor.
     * @param npi       The National Provider Identification of the doctor.
     */
    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);  // Call the constructor of the Provider class
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * Returns the specialty of the doctor.
     *
     * @return The doctor's specialty.
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    public Location geLocation() {
        return super.getLocation();
    }
    /**
     * Sets the specialty of the doctor.
     *
     * @param specialty The new specialty of the doctor.
     */
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    /**
     * Returns the National Provider Identification (NPI) of the doctor.
     *
     * @return The doctor's NPI.
     */
    public String getNpi() {
        return npi;
    }

    /**
     * Sets the National Provider Identification (NPI) of the doctor.
     *
     * @param npi The new NPI for the doctor.
     */
    public void setNpi(String npi) {
        this.npi = npi;
    }

    /**
     * Returns the charge per visit based on the doctor's specialty.
     *
     * @return The rate per visit (based on specialty).
     */
    @Override
    public int rate() {
        return specialty.getCharge();  // Return the rate based on the doctor's specialty
    }

    /**
     * Returns a string representation of the doctor, including the profile, specialty, and NPI.
     *
     * @return A string representing the doctor's profile, specialty, and NPI.
     */
    @Override
    public String toString() {
        return super.toString() + " [" + specialty.name() + ", #" + npi + "]";
    }
}

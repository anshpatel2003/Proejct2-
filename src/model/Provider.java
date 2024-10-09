package model;

/**
 * Represents a provider in the clinic system.
 * This is an abstract class that extends the Person class and includes the provider's location.
 * Providers have a charging rate for seeing patients, implemented in subclasses.
 * @author Your Name
 */
public abstract class Provider extends Person {


    private Location location;  // The practice location of the provider

    /**
     * Constructs a Provider object with the specified profile and location.
     *
     * @param profile  The profile of the provider (inherited from Person).
     * @param location The practice location of the provider.
     */
    public Provider(Profile profile, Location location) {
        super(profile);  // Call the constructor of the Person class
        this.location = location;
    }

    /**
     * Returns the location of the provider's practice.
     *
     * @return The location of the provider.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the provider's practice.
     *
     * @param location The new location for the provider.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Abstract method that returns the provider's rate for seeing a patient.
     * Subclasses must implement this method to define the charging rate.
     *
     * @return The charge per visit for the provider.
     */
    public abstract int rate();

    /**
     * Returns a string representation of the provider, including the profile and location.
     *
     * @return A string representing the provider's profile and location.
     */
    @Override
    public String toString() {
        return profile.toString() + " Location: " + location.toString();
    }
}

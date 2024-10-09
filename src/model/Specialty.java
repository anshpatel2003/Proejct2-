package model;

/**
 * @author Jeet Soni, Ansh Patel
 */


/**
 * The Specialty enum represents various medical specialties, each associated with a specific charge.
 * The available specialties are:
 * - FAMILY: General family practice with a charge of $250.
 * - PEDIATRICIAN: Medical care for children with a charge of $300.
 * - ALLERGIST: Specialized care for allergies with a charge of $350.
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    /**
     * Constructor for the Specialty enum.
     *
     * @param charge The charge associated with the medical specialty.
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * Retrieves the charge associated with the specialty.
     *
     * @return The charge amount in dollars.
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Provides a string representation of the specialty, including its name and charge.
     *
     * @return A string containing the name of the specialty and the associated charge.
     */
    @Override
    public String toString() {
        return name() + " (Charge: $" + charge + ")";
    }
}

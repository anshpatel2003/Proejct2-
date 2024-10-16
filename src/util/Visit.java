package util;

/**
 * Represents a visit, which could be a completed appointment.
 * This class is used to create a linked list of visits for a patient.
 * @author Your Name
 */
public class Visit {

    private int charge;  // Charge for the visit
    private Visit next;  // Pointer to the next visit (linked list)

    /**
     * Constructs a visit with a specified charge.
     * @param charge The cost of the visit.
     */
    public Visit(int charge) {
        this.charge = charge;
        this.next = null;  // By default, no next visit
    }

    /**
     * Returns the charge for the visit.
     * @return The cost of the visit.
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Returns the next visit in the linked list.
     * @return The next visit.
     */
    public Visit getNext() {
        return next;
    }

    /**
     * Sets the next visit in the linked list.
     * @param next The next visit to be set.
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    /**
     * Returns a string representation of the visit.
     * @return The string representation of the visit.
     */
    @Override
    public String toString() {
        return "Visit [Charge=" + charge + "]";
    }

   
}

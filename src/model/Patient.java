package model;
import util.Visit;
/**
 * Represents a patient in the clinic system.
 * Each patient has a profile and a linked list of visits (completed appointments).
 * Extends the Person class to inherit the profile information.
 * @author Your Name
 */
public class Patient extends Person {

    private Visit visits;  // Head of the linked list of visits (completed appointments)

    /**
     * Constructs a Patient object with a specified profile.
     * Initially, the patient has no visits.
     *
     * @param profile The profile of the patient.
     */
    public Patient(Profile profile) {
        super(profile);  // Call the constructor of the Person class
        this.visits = null;  // Initially, the patient has no visits
    }

    /**
     * Adds a completed visit to the patient's list of visits.
     * If there are no visits, the new visit becomes the first in the list.
     * Otherwise, it traverses the list and appends the visit at the end.
     *
     * @param visit The visit to be added to the patient's list of completed visits.
     */
    public void addVisit(Visit visit) {
        if (visits == null) {
            visits = visit;  // If no visits exist, this is the first one
        } else {
            Visit current = visits;
            while (current.getNext() != null) {
                current = current.getNext();  // Traverse to the end of the list
            }
            current.setNext(visit);  // Append the new visit
        }
    }

    /**
     * Calculates the total charge for all the patient's visits.
     * Traverses the linked list of visits and sums up the charges.
     *
     * @return The total charge from all visits.
     */
    public int charge() {
        int totalCharge = 0;
        Visit current = visits;
        while (current != null) {
            totalCharge += current.getCharge();  // Sum up the charges for each visit
            current = current.getNext();  // Move to the next visit in the list
        }
        return totalCharge;
    }

    /**
     * Returns a string representation of the patient, including their profile and completed visits.
     *
     * @return A string representing the patient's profile and their visits.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        

        return result.toString();
    }

    /**
     * Returns the list of completed visits.
     *
     * @return The head of the linked list of visits.
     */
    public Visit getVisits() {
        return visits;
    }

    /**
     * Sets the head of the linked list of visits.
     *
     * @param visits The new head of the linked list.
     */
    public void setVisits(Visit visits) {
        this.visits = visits;
    }


    public void removeVisit(Visit visit) {
        if (visits == null) {
            return;
        }
        if (visits.equals(visit)) {
            visits = visits.getNext();
            return;
        }
        Visit current = visits;
        while (current.getNext() != null) {
            if (current.getNext().equals(visit)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }

  



}       


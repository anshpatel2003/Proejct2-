/**
 * @author Jeet Soni, Ansh Patel
 */


package util;
import model.Technician;

/**
 * A circular linked list to manage a rotation of technicians.
 * This list maintains a circular structure, where the last node points back to the head.
 */
public class CircularTechnicianList {
    private TechnicianNode head;   // Head of the circular list
    private TechnicianNode current;  // Pointer to the current technician in the rotation

    /**
     * Inner class representing each node in the circular list, holding a Technician and a reference to the next node.
     */
    private class TechnicianNode {
        Technician technician;   // The technician object
        TechnicianNode next;     // Reference to the next node in the list

        TechnicianNode(Technician technician) {
            this.technician = technician;
        }
    }

    /**
     * Adds a new technician to the circular list. If the list is empty, the head and current
     * technician are initialized. Otherwise, the new technician is added to the end of the list
     * and linked back to the head.
     *
     * @param technician The technician to add to the list.
     */
    public void addTechnician(Technician technician) {
        TechnicianNode newNode = new TechnicianNode(technician);
        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            TechnicianNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
        if (current == null) {
            current = head;  // Initialize the current technician to the head of the list
        }
    }

    /**
     * Retrieves the next technician in the circular rotation.
     * Moves the current technician pointer to the next node and returns the technician.
     *
     * @return The next technician in the rotation.
     */
    public Technician getNextTechnician() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.technician;
    }

    /**
     * Resets the current technician pointer back to the head of the list.
     */
    public void resetCurrentTechnician() {
        current = head;
    }

    /**
     * Returns the current technician without advancing the rotation.
     *
     * @return The current technician in the rotation.
     */
    public Technician getCurrentTechnician() {
        if (current == null) {
            return null;
        }
        return current.technician;
    }

    /**
     * Checks if the circular list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the technician at the head of the circular list.
     *
     * @return The head technician.
     */
    public Technician gethead() {
        return head.technician;
    }
}

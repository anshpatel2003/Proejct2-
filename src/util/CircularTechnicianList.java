package util;
import model.Technician;

/*
 * The CircularTechnicianList class represents a circular linked list of technicians.
 * It contains methods to add a technician to the list, get the next technician in the list, reset the current
 * technician to the head of the list, get the current technician, and check if the list is empty.
 * The CircularTechnicianList class is used by the ClinicManager class to manage the list of technicians.
 * @auhtor Ansh Patel, Jeet Soni
 */
public class CircularTechnicianList {
    private TechnicianNode head;
    private TechnicianNode current;

    /**
     * The TechnicianNode class represents a node in the circular linked list of technicians.
     * It contains a reference to a technician object and a reference to the next node in the list.
     * @author Ansh Patel, Jeet Soni
     */
    private class TechnicianNode {
        Technician technician;
        TechnicianNode next;

        TechnicianNode(Technician technician) {
            this.technician = technician;
        }
    }

    /**
     * Constructs a CircularTechnicianList object with an empty list of technicians.
     * The head and current references are set to null.
     * The list is initially empty.
     * @param head The head of the list.
     *  
     *
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
            current = head;
        }
    }

    /**
     * Returns the next technician in the list.
     * Otherwise, updates the current technician to the next technician in the list and returns the current technician.
     * @return The next technician in the list.
     */
    public Technician getNextTechnician() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.technician;
    }

    /**
     * Resets the current technician to the head of the list.
     */
    public void resetCurrentTechnician() {
        current = head;
    }

    /**
     * Returns the current technician in the list.
     * @return The current technician in the list.
     */
    public Technician getCurrentTechnician() {
        if (current == null) {
            return null;
        }
        return current.technician;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Technician gethead(){
        return head.technician;
    }
   

}

package util;
import model.Technician;


public class CircularTechnicianList {
    private TechnicianNode head;
    private TechnicianNode current;

    // Inner class representing each node in the circular list
    private class TechnicianNode {
        Technician technician;
        TechnicianNode next;

        TechnicianNode(Technician technician) {
            this.technician = technician;
        }
    }

    // Method to add technicians to the circular list
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

    // Method to get the next technician in the rotation
    public Technician getNextTechnician() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.technician;
    }

    // Method to reset the current technician to the head of the list
    public void resetCurrentTechnician() {
        current = head;
    }

    // Method to get the current technician
    public Technician getCurrentTechnician() {
        if (current == null) {
            return null;
        }
        return current.technician;
    }

    // Method to check if the circular list is empty
    public boolean isEmpty() {
        return head == null;
    }

    public Technician gethead(){
        return head.technician;
    }
   

}

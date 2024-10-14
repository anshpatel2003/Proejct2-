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
            newNode.next = head;  // Pointing to itself to make it circular
        } else {
            TechnicianNode temp = head;
            while (temp.next != head) {  // Traverse to the last node
                temp = temp.next;
            }
            temp.next = newNode;  // Set the new node at the end
            newNode.next = head;  // Make it circular by pointing back to head
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
        Technician technician = current.technician;
        current = current.next;  // Move to the next technician in the rotation
        return technician;
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }
}

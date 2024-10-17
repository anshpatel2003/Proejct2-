package util;
import model.Technician;


public class CircularTechnicianList {
    private TechnicianNode head;
    private TechnicianNode current;


    private class TechnicianNode {
        Technician technician;
        TechnicianNode next;

        TechnicianNode(Technician technician) {
            this.technician = technician;
        }
    }

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

    public Technician getNextTechnician() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.technician;
    }

    public void resetCurrentTechnician() {
        current = head;
    }

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

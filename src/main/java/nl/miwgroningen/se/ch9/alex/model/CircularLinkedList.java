package nl.miwgroningen.se.ch9.alex.model;

import java.util.LinkedList;

/**
 * @author Alex Kuiper <al.kuiper@st.hanze.nl>
 * <p>
 * Circular Linked List class
 */
public class CircularLinkedList extends LinkedList {
    private Node head = null;
    private Node tail = null;

    public void addNode(String note) {
        Node newNode = new Node(note);

        if (head == null) {
            head = newNode;
        } else {
            tail.nextNode = newNode;
        }

        tail = newNode;
        tail.nextNode = head;
    }


    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("Lijst is leeg");
        } else {
            System.out.println("Noten in de lijst: ");
            do {
                System.out.print(" " + current.value);
                current = current.nextNode;
            } while (current != head);
            System.out.println();
        }
    }

    public void setHead(int index) {
        if (index > 1) {
            for (int i = 1; i < index; i++) {
                head = head.nextNode;
            }
        } else {
            this.head = head;
        }
    }
}

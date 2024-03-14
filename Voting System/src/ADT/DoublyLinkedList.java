/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADT;

/**
 *
 * @author Asus
 */
import java.util.Iterator;

public class DoublyLinkedList<T> implements List<T> {

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public DoublyLinkedList() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry); // create the new node

        if (isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
        } else {
            newNode.previous = lastNode; // set the previous reference of the new node to the current last node
            lastNode.next = newNode; // set the next reference of the current last node to the new node
            lastNode = newNode; // update the last node to be the new node
        }

        numberOfEntries++;
    }

    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);

            if (isEmpty() || (newPosition == 1)) { // case 1: add to beginning of list
                newNode.next = firstNode;
                firstNode = newNode;
            } else {								// case 2: list is not empty and newPosition > 1
                Node nodeBefore = firstNode;
                for (int i = 1; i < newPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
                }

                newNode.next = nodeBefore.next;	// make new node point to current node at newPosition
                nodeBefore.next = newNode;		// make the node before point to the new node
            }

            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    public boolean isEmpty() {
        boolean result = numberOfEntries == 0;
        return result;
    }

    @Override
    public boolean remove() {
        if (isEmpty()) {
            return false;
        } else {
            lastNode = lastNode.previous;
            lastNode.next = null;
            numberOfEntries--;
        }
        return true;
    }

    @Override
    public boolean remove(T data) {
        Node currentNode = firstNode;
        if (currentNode == lastNode) {
            remove();
        }
        if (currentNode != null && currentNode.data.equals(data)) {
            firstNode = currentNode.next;
            if (firstNode != null) {
                firstNode.previous = null;
            }
            numberOfEntries--;
            return true;
        }
        while (currentNode != null && !currentNode.data.equals(data)) {
            currentNode = currentNode.next;
        }

        // handle case where node with data is found
        if (currentNode != null) {
            currentNode.previous.next = currentNode.next;
            if (currentNode.next != null) {
                currentNode.next.previous = currentNode.previous;
            }
            numberOfEntries--;
            return true;
        }
        return false;
    }

    public T getFirstNode() {
        if (firstNode == null) {
            System.out.println("Empty");
        }
        return firstNode.data;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return found;
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public void clear() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public Iterator<T> getIterator() {
        return new DoublyLinkedListIterator();
    }

    @Override
    public void printListForward() {
        if (firstNode == null) {
            System.out.println("List is empty");
            return;
        }

        Node currentNode = firstNode;
        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.next;
            System.out.println();
        }
    }

    @Override
    public void printListBackward() {
        if (lastNode == null) {
            System.out.println("List is empty");
            return;
        }

        Node currentNode = lastNode;
        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.previous;
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    private class DoublyLinkedListIterator implements Iterator<T> {

        private Node currentNode = firstNode;

        public boolean hasNext() {
            return currentNode != null;
        }

        public T next() {
            T currentData = null;
            if (hasNext()) {
                currentData = currentNode.data;
                currentNode = currentNode.next;
            }
            return currentData;
        }
    }

    private class Node {

        private T data;
        private Node next;
        private Node previous;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return previous;
        }

    }

}

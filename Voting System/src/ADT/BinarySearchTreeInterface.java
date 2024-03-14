package ADT;

import java.util.Iterator;

public interface BinarySearchTreeInterface<T extends Comparable<T>> {

    public boolean search(T entry); // search specific entry/item from tree

    public T getEntry(T entry); // get the entry from tree

    public boolean add(T newEntry);// add new entry to tree

    public T remove(T entry); // remove the specific entry from tree

    public void clear(); // clear all the item of tree

    public int getSize(); // get the size of binary search tree 

    public Iterator<T> getInorderIterator(); //to get the entry from smallest to largest

    public Iterator<T> getInorderIterator_reverse(); // get the entry from largest to smallest

    public void printTree(); //display all the item in tree

}

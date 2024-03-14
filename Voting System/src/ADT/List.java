/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ADT;

import java.util.Iterator;

/**
 *
 * @author Asus
 */
public interface List<T> {

    void add(T newEntry);

    boolean add(int newPosition, T newEntry);

    boolean isEmpty();

    boolean remove();

    boolean remove(T data);

    T getFirstNode();

    boolean contains(T data);

    int getNumberOfEntries();

    void clear();

    Iterator<T> getIterator();

    void printListBackward();

    void printListForward();
}

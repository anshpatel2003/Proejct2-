package util;

import java.util.Iterator;

/**
 * A generic list class that implements a resizable array to store elements of any type.
 * Implements only the API specified without using any additional libraries or methods.
 * Implements Iterable<E> to allow iteration over the list's elements.
 * @param <E> The type of elements stored in the list.
 * @author Your Name
 */
public class List<E> implements Iterable<E> {

    private E[] objects;  // Array to store elements
    private int size;     // Number of elements in the list

    /**
     * Default constructor to initialize the list with a capacity of 4.
     */
    public List() {
        objects = (E[]) new Object[4];  // Initial capacity of 4
        size = 0;
    }

    /**
     * Finds the index of a specific element in the list.
     * @param e The element to search for.
     * @return The index of the element if found, otherwise -1.
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases the capacity of the list when it is full.
     */
    private void grow() {
        E[] newArray = (E[]) new Object[objects.length + 4];  // Increase the capacity by 4
        System.arraycopy(objects, 0, newArray, 0, objects.length);  // Copy old elements to the new array
        objects = newArray;
    }

    /**
     * Checks if the list contains the specified element.
     * @param e The element to check for.
     * @return True if the list contains the element, false otherwise.
     */
    public boolean contains(E e) {
        return find(e) != -1;
    }

    /**
     * Adds a new element to the list. Duplicates are not allowed.
     * @param e The element to add.
     */
    public void add(E e) {
        if (contains(e)) {
            return;  // Do not add duplicates
        }
        if (size == objects.length) {
            grow();  // Increase capacity if needed
        }
        objects[size++] = e;
    }

    /**
     * Removes an element from the list.
     * @param e The element to remove.
     */
    public void remove(E e) {
        int index = find(e);
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                objects[i] = objects[i + 1];  // Shift elements to the left
            }
            objects[--size] = null;  // Reduce size and clear the last reference
        }
    }

    /**
     * Checks if the list is empty.
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves the element at the specified index.
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return objects[index];
    }

    /**
     * Sets the element at the specified index.
     * @param index The index to set.
     * @param e The element to set at the specified index.
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        objects[index] = e;
    }

    /**
     * Returns the index of a specific element in the list.
     * @param e The element to search for.
     * @return The index of the element if found, otherwise -1.
     */
    public int indexOf(E e) {
        return find(e);
    }

    /**
     * Returns a new ListIterator for the list.
     * @return A ListIterator instance for this list.
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Internal class to iterate over the list.
     */
    private class ListIterator implements Iterator<E> {
        private int currentIndex = 0;

        /**
         * Checks if there are more elements in the list to iterate over.
         * @return True if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the list.
         * @return The next element.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements.");
            }
            return objects[currentIndex++];
        }
    }
}

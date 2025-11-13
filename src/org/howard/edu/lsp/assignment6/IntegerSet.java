package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mathematical set of integers with no duplicates.
 * Supports standard set operations including union, intersection, difference, and complement.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Assignment 6
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the internal representation of the set.
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return the number of elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the 2 sets are equal, false otherwise.
     * Two sets are equal if they contain all of the same values in ANY order.
     * This overrides the equals method from the Object class.
     * 
     * @param o the object to compare with
     * @return true if the sets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        IntegerSet other = (IntegerSet) o;
        
        // Compare sizes first for efficiency
        if (this.length() != other.length()) {
            return false;
        }
        
        // Check if all elements in this set are in the other set
        for (Integer element : this.set) {
            if (!other.contains(element)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Returns true if the set contains the value, otherwise false.
     * 
     * @param value the value to check for
     * @return true if the set contains the value, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * 
     * @return the largest item in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        
        int max = set.get(0);
        for (int i = 1; i < set.size(); i++) {
            if (set.get(i) > max) {
                max = set.get(i);
            }
        }
        return max;
    }

    /**
     * Returns the smallest item in the set.
     * 
     * @return the smallest item in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        
        int min = set.get(0);
        for (int i = 1; i < set.size(); i++) {
            if (set.get(i) < min) {
                min = set.get(i);
            }
        }
        return min;
    }

    /**
     * Adds an item to the set or does nothing if already present.
     * Ensures no duplicates are added to the set.
     * 
     * @param item the item to add to the set
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set or does nothing if not there.
     * 
     * @param item the item to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Set union: modifies this to contain all unique elements in this or other.
     * Adds all elements from the other set that are not already in this set.
     * 
     * @param other the other IntegerSet to union with
     */
    public void union(IntegerSet other) {
        for (Integer element : other.set) {
            this.add(element);
        }
    }

    /**
     * Set intersection: modifies this to contain only elements in both sets.
     * Removes all elements from this set that are not in the other set.
     * 
     * @param other the other IntegerSet to intersect with
     */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /**
     * Set difference (this \ other): modifies this to remove elements found in other.
     * Removes all elements from this set that are present in the other set.
     * 
     * @param other the other IntegerSet to subtract from this set
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Set complement: modifies this to become (other \ this).
     * This set becomes the elements that are in other but not in this.
     * 
     * @param other the other IntegerSet to complement with
     */
    public void complement(IntegerSet other) {
        // Create a copy of other to avoid modifying it
        List<Integer> otherCopy = new ArrayList<>(other.set);
        // Remove elements that are in this set
        otherCopy.removeAll(this.set);
        // Replace this set with the complement
        this.set = otherCopy;
    }

    /**
     * Returns true if the set is empty, false otherwise.
     * 
     * @return true if the set is empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a String representation of the set.
     * Overrides Object.toString() to return elements in square brackets, e.g., [1, 2, 3].
     * 
     * @return a String representation of the set
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < set.size(); i++) {
            sb.append(set.get(i));
            if (i < set.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}






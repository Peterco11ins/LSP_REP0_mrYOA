package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * JUnit 5 test suite for IntegerSet class.
 * Tests all public methods including normal cases and edge cases.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Assignment 6
 */
public class IntegerSetTest {
    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;

    /**
     * Sets up test fixtures before each test method.
     * Creates fresh IntegerSet instances for testing.
     */
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        emptySet = new IntegerSet();
        
        // Set up set1 with some values
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Set up set2 with overlapping values
        set2.add(2);
        set2.add(3);
        set2.add(4);
    }

    /**
     * Tests the clear() method.
     */
    @Test
    @DisplayName("Test clear method")
    public void testClear() {
        assertFalse(set1.isEmpty());
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    /**
     * Tests the length() method.
     */
    @Test
    @DisplayName("Test length method")
    public void testLength() {
        assertEquals(0, emptySet.length());
        assertEquals(3, set1.length());
        set1.add(4);
        assertEquals(4, set1.length());
        set1.remove(1);
        assertEquals(3, set1.length());
    }

    /**
     * Tests the equals() method.
     */
    @Test
    @DisplayName("Test equals method")
    public void testEquals() {
        IntegerSet set3 = new IntegerSet();
        set3.add(3);
        set3.add(2);
        set3.add(1);
        
        // Same elements, different order
        assertTrue(set1.equals(set3));
        
        // Different sets
        assertFalse(set1.equals(set2));
        
        // Empty sets
        IntegerSet emptySet2 = new IntegerSet();
        assertTrue(emptySet.equals(emptySet2));
        
        // Self equality
        assertTrue(set1.equals(set1));
        
        // Null comparison
        assertFalse(set1.equals(null));
        
        // Different class
        assertFalse(set1.equals("not a set"));
    }

    /**
     * Tests the contains() method.
     */
    @Test
    @DisplayName("Test contains method")
    public void testContains() {
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(4));
        assertFalse(emptySet.contains(1));
    }

    /**
     * Tests the largest() method.
     */
    @Test
    @DisplayName("Test largest method")
    public void testLargest() {
        assertEquals(3, set1.largest());
        assertEquals(4, set2.largest());
        
        set1.add(10);
        assertEquals(10, set1.largest());
        
        set1.add(-5);
        assertEquals(10, set1.largest());
        
        // Test exception for empty set
        assertThrows(IllegalStateException.class, () -> {
            emptySet.largest();
        });
    }

    /**
     * Tests the smallest() method.
     */
    @Test
    @DisplayName("Test smallest method")
    public void testSmallest() {
        assertEquals(1, set1.smallest());
        assertEquals(2, set2.smallest());
        
        set1.add(-5);
        assertEquals(-5, set1.smallest());
        
        set1.add(10);
        assertEquals(-5, set1.smallest());
        
        // Test exception for empty set
        assertThrows(IllegalStateException.class, () -> {
            emptySet.smallest();
        });
    }

    /**
     * Tests the add() method.
     */
    @Test
    @DisplayName("Test add method")
    public void testAdd() {
        int initialLength = set1.length();
        set1.add(4);
        assertEquals(initialLength + 1, set1.length());
        assertTrue(set1.contains(4));
        
        // Test adding duplicate (should not add)
        set1.add(4);
        assertEquals(initialLength + 1, set1.length());
        
        // Test adding to empty set
        emptySet.add(1);
        assertTrue(emptySet.contains(1));
        assertEquals(1, emptySet.length());
    }

    /**
     * Tests the remove() method.
     */
    @Test
    @DisplayName("Test remove method")
    public void testRemove() {
        int initialLength = set1.length();
        set1.remove(2);
        assertEquals(initialLength - 1, set1.length());
        assertFalse(set1.contains(2));
        
        // Test removing non-existent element (should do nothing)
        set1.remove(99);
        assertEquals(initialLength - 1, set1.length());
        
        // Test removing from empty set (should do nothing)
        emptySet.remove(1);
        assertTrue(emptySet.isEmpty());
    }

    /**
     * Tests the union() method.
     */
    @Test
    @DisplayName("Test union method")
    public void testUnion() {
        IntegerSet originalSet1 = new IntegerSet();
        originalSet1.add(1);
        originalSet1.add(2);
        originalSet1.add(3);
        
        set1.union(set2);
        
        // Should contain all unique elements from both sets
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertEquals(4, set1.length());
        
        // Test union with empty set
        originalSet1.union(emptySet);
        assertEquals(3, originalSet1.length());
        
        // Test union with self
        originalSet1.union(originalSet1);
        assertEquals(3, originalSet1.length());
    }

    /**
     * Tests the intersect() method.
     */
    @Test
    @DisplayName("Test intersect method")
    public void testIntersect() {
        IntegerSet originalSet1 = new IntegerSet();
        originalSet1.add(1);
        originalSet1.add(2);
        originalSet1.add(3);
        
        originalSet1.intersect(set2);
        
        // Should contain only common elements
        assertFalse(originalSet1.contains(1));
        assertTrue(originalSet1.contains(2));
        assertTrue(originalSet1.contains(3));
        assertFalse(originalSet1.contains(4));
        assertEquals(2, originalSet1.length());
        
        // Test intersection with empty set
        IntegerSet set3 = new IntegerSet();
        set3.add(1);
        set3.add(2);
        set3.intersect(emptySet);
        assertTrue(set3.isEmpty());
        
        // Test intersection with self
        IntegerSet set4 = new IntegerSet();
        set4.add(1);
        set4.add(2);
        set4.intersect(set4);
        assertEquals(2, set4.length());
        assertTrue(set4.contains(1));
        assertTrue(set4.contains(2));
    }

    /**
     * Tests the diff() method.
     */
    @Test
    @DisplayName("Test diff method")
    public void testDiff() {
        IntegerSet originalSet1 = new IntegerSet();
        originalSet1.add(1);
        originalSet1.add(2);
        originalSet1.add(3);
        
        originalSet1.diff(set2);
        
        // Should contain only elements in set1 but not in set2
        assertTrue(originalSet1.contains(1));
        assertFalse(originalSet1.contains(2));
        assertFalse(originalSet1.contains(3));
        assertEquals(1, originalSet1.length());
        
        // Test diff with empty set (should remain unchanged)
        IntegerSet set3 = new IntegerSet();
        set3.add(1);
        set3.add(2);
        set3.diff(emptySet);
        assertEquals(2, set3.length());
        
        // Test diff with self (should become empty)
        IntegerSet set4 = new IntegerSet();
        set4.add(1);
        set4.add(2);
        set4.diff(set4);
        assertTrue(set4.isEmpty());
    }

    /**
     * Tests the complement() method.
     */
    @Test
    @DisplayName("Test complement method")
    public void testComplement() {
        IntegerSet originalSet1 = new IntegerSet();
        originalSet1.add(1);
        originalSet1.add(2);
        originalSet1.add(3);
        
        originalSet1.complement(set2);
        
        // Should become elements in set2 but not in original set1
        assertFalse(originalSet1.contains(1));
        assertFalse(originalSet1.contains(2));
        assertFalse(originalSet1.contains(3));
        assertTrue(originalSet1.contains(4));
        assertEquals(1, originalSet1.length());
        
        // Test complement with empty set
        IntegerSet set3 = new IntegerSet();
        set3.add(1);
        set3.add(2);
        set3.complement(emptySet);
        assertTrue(set3.isEmpty());
        
        // Test complement where other is superset
        IntegerSet set4 = new IntegerSet();
        set4.add(1);
        IntegerSet set5 = new IntegerSet();
        set5.add(1);
        set5.add(2);
        set5.add(3);
        set4.complement(set5);
        assertEquals(2, set4.length());
        assertTrue(set4.contains(2));
        assertTrue(set4.contains(3));
    }

    /**
     * Tests the isEmpty() method.
     */
    @Test
    @DisplayName("Test isEmpty method")
    public void testIsEmpty() {
        assertTrue(emptySet.isEmpty());
        assertFalse(set1.isEmpty());
        
        set1.clear();
        assertTrue(set1.isEmpty());
        
        emptySet.add(1);
        assertFalse(emptySet.isEmpty());
    }

    /**
     * Tests the toString() method.
     */
    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        assertEquals("[]", emptySet.toString());
        
        IntegerSet singleSet = new IntegerSet();
        singleSet.add(5);
        assertEquals("[5]", singleSet.toString());
        
        String set1Str = set1.toString();
        assertTrue(set1Str.startsWith("["));
        assertTrue(set1Str.endsWith("]"));
        assertTrue(set1Str.contains("1"));
        assertTrue(set1Str.contains("2"));
        assertTrue(set1Str.contains("3"));
        
        // Test that toString is order-independent for equals
        IntegerSet set3 = new IntegerSet();
        set3.add(3);
        set3.add(2);
        set3.add(1);
        // Different order may produce different string, but equals should work
        assertTrue(set1.equals(set3));
    }

    /**
     * Tests edge case: operations with disjoint sets.
     */
    @Test
    @DisplayName("Test edge case: disjoint sets")
    public void testDisjointSets() {
        IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(2);
        
        IntegerSet setB = new IntegerSet();
        setB.add(3);
        setB.add(4);
        
        // Intersection of disjoint sets should be empty
        setA.intersect(setB);
        assertTrue(setA.isEmpty());
        
        // Reset and test union
        setA.add(1);
        setA.add(2);
        setA.union(setB);
        assertEquals(4, setA.length());
    }

    /**
     * Tests edge case: duplicate prevention.
     */
    @Test
    @DisplayName("Test edge case: duplicate prevention")
    public void testDuplicatePrevention() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(1);
        set.add(1);
        assertEquals(1, set.length());
        assertTrue(set.contains(1));
        
        set.add(2);
        set.add(2);
        assertEquals(2, set.length());
    }

    /**
     * Tests edge case: mutation checks.
     */
    @Test
    @DisplayName("Test edge case: mutation checks")
    public void testMutation() {
        IntegerSet original = new IntegerSet();
        original.add(1);
        original.add(2);
        
        IntegerSet copy = new IntegerSet();
        copy.add(1);
        copy.add(2);
        
        IntegerSet other = new IntegerSet();
        other.add(3);
        other.add(4);
        
        // Union should modify original, not create new object
        original.union(other);
        assertTrue(original.contains(3));
        assertTrue(original.contains(4));
        // Copy should remain unchanged
        assertEquals(2, copy.length());
        assertFalse(copy.contains(3));
    }
}






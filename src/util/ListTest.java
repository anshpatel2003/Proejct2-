package util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ListTest {

    private List<String> list;

    @Before
    public void setUp() {
        list = new List<>();
    }

    @Test
    public void testAddElement() {
        list.add("Apple");
        assertEquals(1, list.size());
        assertTrue(list.contains("Apple"));
    }

    @Test
    public void testAddDuplicate() {
        list.add("Apple");
        list.add("Apple");
        assertEquals(1, list.size());
    }

    @Test
    public void testAddMultipleElements() {
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        assertEquals(3, list.size());
        assertTrue(list.contains("Apple"));
        assertTrue(list.contains("Banana"));
        assertTrue(list.contains("Cherry"));
    }

    @Test
    public void testAddGrowCapacity() {
        for (int i = 0; i < 10; i++) {
            list.add("Element" + i);
        }
        assertEquals(10, list.size());
        for (int i = 0; i < 10; i++) {
            assertTrue(list.contains("Element" + i));
        }
    }

    @Test
    public void testRemoveElement() {
        list.add("Apple");
        list.add("Banana");
        list.remove("Apple");
        assertEquals(1, list.size());
        assertFalse(list.contains("Apple"));
        assertTrue(list.contains("Banana"));
    }

    @Test
    public void testRemoveNonExistentElement() {
        list.add("Apple");
        list.remove("Banana");
        assertEquals(1, list.size());
        assertTrue(list.contains("Apple"));
    }

    @Test
    public void testRemoveFromEmptyList() {
        list.remove("Apple");
        assertEquals(0, list.size());
    }

    @Test
    public void testRemoveAndReAdd() {
        list.add("Apple");
        list.remove("Apple");
        list.add("Apple");
        assertEquals(1, list.size());
        assertTrue(list.contains("Apple"));
    }

    @Test
    public void testAddRemoveMultiple() {
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.remove("Banana");
        assertEquals(2, list.size());
        assertTrue(list.contains("Apple"));
        assertFalse(list.contains("Banana"));
        assertTrue(list.contains("Cherry"));
    }
}
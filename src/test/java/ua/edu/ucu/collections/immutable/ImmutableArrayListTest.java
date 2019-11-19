package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {

    private ImmutableArrayList list1;
    private ImmutableArrayList list2;
    private ImmutableArrayList list3;

    @Before
    public void setUp() {
        list1 = new ImmutableArrayList();
        Object[] elements = new Object[] {1, 2, 3, 4};
        list2 = new ImmutableArrayList(elements);
        Object[] elements2 = new Object[] {"a", "b", "c"};
        list3 = new ImmutableArrayList(elements2);
    }

    @Test
    public void testAdd() {
        ImmutableList List1 = list1.add(1);
        ImmutableList List2 = list2.add("a");
        assertArrayEquals(new Object[] {1}, List1.toArray());
        assertArrayEquals(new Object[] {1, 2, 3, 4, "a"}, List2.toArray());
    }

    @Test
    public void testAddWithIndex() {
        ImmutableList List2 = list2.add(1,"b");
        ImmutableList List3 = list3.add(2, 13);
        assertArrayEquals(new Object[] {1, "b", 2, 3, 4}, List2.toArray());
        assertArrayEquals(new Object[] {"a", "b", 13, "c"}, List3.toArray());
    }

    @Test
    public void testAddAll() {
        ImmutableList List2 = list2.addAll(new Object[] {"cs", "ba"});
        ImmutableList List3 = list3.addAll(new Object[] {5, 6, 7});
        assertArrayEquals(new Object[] {1, 2, 3, 4, "cs", "ba"}, List2.toArray());
        assertArrayEquals(new Object[] {"a", "b", "c", 5, 6, 7}, List3.toArray());
    }

    @Test
    public void testAddAllWithIndex() {
        ImmutableList List2 = list2.addAll(2, new Object[] {"cs", "ba"});
        ImmutableList List3 = list3.addAll(1, new Object[] {5, 6, 7});
        assertArrayEquals(new Object[] {1, 2, "cs", "ba", 3, 4}, List2.toArray());
        assertArrayEquals(new Object[] {"a", 5, 6, 7, "b", "c"}, List3.toArray());
    }

    @Test
    public void testGet() {
        assertEquals(3, list2.get(2));
        assertEquals("a", list3.get(0));
        list3 = list3.add(0, "cs");
        assertEquals("cs", list3.get(0));
    }

    @Test
    public void testRemove() {
        ImmutableList List2 = list2.remove(2);
        ImmutableList List3 = list3.remove(1);
        assertArrayEquals(new Object[] {1, 2, 4}, List2.toArray());
        assertArrayEquals(new Object[] {"a", "c"}, List3.toArray());
    }

    @Test
    public void testSet() {
        ImmutableList List2 = list2.set(2, "cs");
        ImmutableList List3 = list3.set(0, 13);
        assertArrayEquals(new Object[] {1, 2, "cs", 4}, List2.toArray());
        assertArrayEquals(new Object[] {13, "b", "c"}, List3.toArray());
    }

    @Test
    public void testIndexOf() {
        int index1 = list2.indexOf(4);
        int index2 = list3.indexOf("c");
        assertEquals(3, index1);
        assertEquals(2, index2);
    }

    @Test
    public void testSize() {
        int size1 = list1.size();
        int size2 = list2.size();
        int size3 = list3.size();
        assertEquals(0, size1);
        assertEquals(4, size2);
        assertEquals(3, size3);
    }

    @Test
    public void testClear() {
        ImmutableList List3 = list3.clear();
        assertArrayEquals(List3.toArray(), new Object[] {});
    }

    @Test
    public void testIsEmpty() {
        assertFalse(list2.isEmpty());
        assertTrue(list1.isEmpty());
        assertFalse(list3.isEmpty());
        list3 = list3.clear();
        assertTrue(list3.isEmpty());
    }

    @Test
    public void testToArray() {
        assertArrayEquals(list2.toArray(), new Object[] {1, 2, 3, 4});
        assertArrayEquals(list3.toArray(), new Object[] {"a", "b", "c"});
        assertArrayEquals(list1.toArray(), new Object[] {});
    }

    @Test
    public void testToString() {
        assertEquals(list2.toString(), "1, 2, 3, 4");
        assertEquals(list1.toString(), "");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptions1() {
        list1.set(10, 'a');
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptions2() {
        list1.remove(10);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptions3() {
        list1.get(6);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptions4() {
        list1.addAll(5, new Object[] {1, 2});
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptions5() {
        list1.add(4, 1);
    }
}
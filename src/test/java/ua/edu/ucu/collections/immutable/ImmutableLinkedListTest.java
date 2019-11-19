package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableLinkedList list1;
    private ImmutableLinkedList list2;

    @Before
    public void SetUp() {
        list1 = new ImmutableLinkedList();
        Object[] List = {1, 2, 3};
        list2 = new ImmutableLinkedList(List);
    }

    @Test
    public void testEmptyLinkedList() {
        assertNull(list1.getRoot().getElement());
        assertNull(list1.getEnd().getElement());
        assertEquals(0, list1.size());
    }

    @Test
    public void testImmutableLinkedListWithEls() {
        assertEquals(1, list2.getRoot().getElement());
        assertEquals(3, list2.getEnd().getElement());
        assertEquals(3, list2.size());
    }

    private void testEquals(Object[] arr, ImmutableLinkedList lst) {
        ImmutableLinkedList newList = new ImmutableLinkedList(arr);
        assertEquals(newList.toString(), lst.toString());
        assertEquals(arr.length, lst.size());
    }

    @Test
    public void testAddAtLastPosition() {
        this.testEquals(new Object[]{1, 2, 3, 4}, list2.add(4));
        this.testEquals(new Object[]{1}, list1.add(1));
        this.testEquals(new Object[]{1, 2, 3, 4}, list2.addLast(4));
        this.testEquals(new Object[]{1}, list1.addLast(1));
        this.testEquals(new Object[]{}, list1.addLast(1).removeFirst());
    }

    @Test
    public void testAddatFirstPosition() {
        this.testEquals(new Object[]{0, 1, 2, 3}, list2.add(0, 0));
        this.testEquals(new Object[]{1}, list1.add(0, 1));
        this.testEquals(new Object[]{0, 1, 2, 3}, list2.addFirst(0));
        this.testEquals(new Object[]{1}, list1.addFirst(1));
    }

    @Test
    public void testAddatAnyPosition() {
        this.testEquals(new Object[]{1, 2, 2.1, 3}, list2.add(2, 2.1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddInvalidIndextooBig() {
        ImmutableLinkedList newImLL = list2.add(5, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddInvalidIndexNegative() {
        ImmutableLinkedList newImLL1 = list2.add(-5, 2);
    }

    @Test
    public void testAddAllatAnyPosition() {
        Object[] toAdd = {"a", "b", "c"};
        this.testEquals(new Object[]{"a", "b", "c", 1, 2, 3},
                list2.addAll(0, toAdd));
        this.testEquals(new Object[]{1, 2, 3, "a", "b", "c"},
                list2.addAll(toAdd));
        Object[] l = list2.toArray();
        this.testEquals(new Object[]{1, 2, 3},
                list1.addAll(l));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllInvalidIndex() {
        Object[] toAdd = {1, 2, 3};
        ImmutableLinkedList newl = list2.addAll(5, toAdd);
    }

    @Test
    public void testGet() {
        assertEquals(1, list2.get(0));
        assertEquals(3, list2.get(2));
    }

    @Test
    public void testRemove() {
        this.testEquals(new Object[]{2, 3},
                list2.removeFirst());
        this.testEquals(new Object[]{1, 3},
                list2.remove(1));
        this.testEquals(new Object[]{1, 2},
                list2.removeLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndex() {
        list2.remove(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndexFromEmptyList() {
        list1.remove(6);
    }

    @Test
    public void testSet() {
        this.testEquals(new Object[]{"cs", 2, 3},
                list2.set(0, "cs"));
        this.testEquals(new Object[]{1, 2, "cs"},
                list2.set(2, "cs"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetInvalidIndex() {
        list2.set(6, "cs");
    }

    @Test
    public void testindexOf() {
        assertEquals(-1, list2.indexOf("cs"));
        assertEquals(1, list2.indexOf(2));
    }

    @Test
    public void testSize() {
        assertEquals(0, list1.size());
        assertEquals(3, list2.size());
        list2 = list2.add("cs");
        assertEquals(4, list2.size());
    }

    @Test
    public void testClear() {
        assertEquals(list1.toString(), list2.clear().toString());
        assertEquals(0, list2.clear().size());
        assertNull(list2.clear().getRoot().getElement());
        assertNull(list2.clear().getEnd().getElement());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list1.isEmpty());
        assertFalse(list2.isEmpty());
        list1 = list1.add("cs");
        assertFalse(list1.isEmpty());
    }

    @Test
    public void testToArray() {
        Object[] newArr = {1, 2, 3};
        assertArrayEquals(newArr, list2.toArray());
    }

    @Test
    public void testGetFirst() {
        assertEquals(1, list2.getFirst());
        assertNull(list1.getFirst());
    }

    @Test
    public void testGetLast() {
        assertEquals(3, list2.getLast());
        assertNull(list1.getLast());
    }
}
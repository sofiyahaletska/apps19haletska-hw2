package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {

    private Stack stack1;
    private Stack stack2;

    @Before
    public void setUp() {
        stack1 = new Stack(new Object[] {"ua", "edu", "ucu", "apps"});
        stack2 = new Stack();
    }

    @Test
    public void testPeek() {
        assertEquals("apps", stack1.peek());
    }

    @Test
    public void testPop() {
        Stack expected = new Stack(new Object[] {"ua", "edu", "ucu"});
        assertEquals("apps", stack1.pop());
        assertEquals(expected.peek(), stack1.peek());
    }

    @Test
    public void testPush() {
        stack2.push("cs");
        stack2.push("ba");
        assertEquals("ba", stack2.pop());
        assertEquals("cs", stack2.pop());
        assertNull(stack2.pop());
    }
}
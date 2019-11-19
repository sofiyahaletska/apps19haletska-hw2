package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    private Queue queue1;
    private Queue queue2;

    @Before
    public void SetUp() {
        Object[] newList = {"ucu", "edu", "ua"};
        queue1 = new Queue(newList);
        queue2 = new Queue();
    }

    @Test
    public void testPeek() {
        assertEquals("ucu", queue1.peek());
        assertNull(queue2.peek());
    }

    @Test
    public void testEnqueue() {
        Object[] List = {"ucu", "edu", "ua", "apps"};
        Queue queue = new Queue(List);
        queue1.enqueue("apps");
        assertEquals(queue.toString(), queue1.toString());
    }

    @Test
    public void testDequeue() {
        Object[] List = {"edu", "ua"};
        Queue queue = new Queue(List);
        assertEquals("ucu", queue1.dequeue());
        assertEquals(queue.toString(), queue1.toString());
    }
}
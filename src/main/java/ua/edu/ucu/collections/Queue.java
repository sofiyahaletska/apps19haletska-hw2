package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

class Queue {
    private ImmutableLinkedList queue;

    Queue() {
        queue = new ImmutableLinkedList();
    }

    Queue(Object[] objs) {
        queue = new ImmutableLinkedList(objs);
    }

    Object peek() { return queue.getFirst(); }

    Object dequeue()
    {
        Object first = queue.getFirst();
        queue = queue.removeFirst();
        return first;
    }

    void enqueue(Object e)
    {
        queue = queue.addLast(e);
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList stack;

    Stack() {
        this.stack = new ImmutableLinkedList();
    }

    Stack(Object[] els) {
        this.stack = new ImmutableLinkedList(els);
    }

    Object peek() {
        return stack.getLast();
    }

    Object pop() {
        Object elem = stack.getLast();
        stack = stack.removeLast();
        return elem;
    }

    void push(Object e) {
        stack = stack.add(e);
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
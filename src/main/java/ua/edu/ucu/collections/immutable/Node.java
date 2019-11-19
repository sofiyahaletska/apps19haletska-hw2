package ua.edu.ucu.collections.immutable;

class Node {
    private Object element;
    private Node next;
    private Node previous;

    Node() {
        previous = null;
        element = null;
        next = null;
    }

    Object getElement() {
        return element;
    }

    void setElement(Object val) {
        this.element = val;
    }

    Node getNext() {
        return next;
    }

    void setNext(Node next) {
        this.next = next;
    }
    Node getPrevious() {
        return previous;
    }

    void setPrevious(Node previous) {
        this.previous = previous;
    }
}
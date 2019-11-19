package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private Node end;
    private Node root;

    public ImmutableLinkedList() {
        root = new Node();
        end = root;
    }
    public ImmutableLinkedList(Object[] el) {
        root = new Node();
        if (el.length > 0) {
            root.setElement(el[0]);
            Node currentNode = root;
            for (int i = 1; i < el.length; i++) {
                Node newNode = new Node();
                newNode.setElement(el[i]);
                newNode.setPrevious(currentNode);
                currentNode.setNext(newNode);
                currentNode = newNode;
            }
            end = currentNode;
        }
        else {
            end = root;
        }
    }
    Node getRoot() {
        return root;
    }

    Node getEnd() {
        return end;
    }

    private Node NodeWithIndex(int index) {
        Node currentNode = root;
        for (int counter = 0; counter < index; counter++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }


    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size(), e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        Object[] arr = new Object[1];
        arr[0] = e;
        return addAll(index, arr);
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        checkBounds(index);
        if (index == 0) {
            ImmutableLinkedList Copy = new ImmutableLinkedList(c);
            if (root.getElement() != null) {
                Copy.end.setNext(root);
                Copy.end.getNext().setPrevious(Copy.end);
                Copy.end = this.getEnd();
            }
            return Copy;
        }
        else if (index == size()) {
            ImmutableLinkedList Copy = CopyOfLinkedList();
            for(Object el: c){
                Copy = Copy.addLast(el);
            }
            return Copy;
        }
        else {
            ImmutableLinkedList Copy = CopyOfLinkedList();
            for(int i = index; i < c.length + index; i++){
                Object el = c[i - index];
                Node nextEl = new Node();
                nextEl.setElement(el);
                Node currentNode = Copy.NodeWithIndex(i);
                Node previousNode = currentNode.getPrevious();
                nextEl.setPrevious(previousNode);
                previousNode.setNext(nextEl);
                nextEl.setNext(currentNode);
                currentNode.setPrevious(nextEl);
            }
            return Copy;
        }
    }

    @Override
    public Object get(int index) {
        checkBounds(index);
        return NodeWithIndex(index).getElement();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        if (index == 0){
            return removeFirst();
        }
        else if (index == size()){
            return removeLast();
        }
        else{
            ImmutableLinkedList Copy = CopyOfLinkedList();
            checkBounds(index);
            if (size() > 1){
                Node previousNode = Copy.NodeWithIndex(index).getPrevious();
                Node nextNode = Copy.NodeWithIndex(index).getNext();
                if (previousNode != null){
                    previousNode.setNext(nextNode);
                }
                if (nextNode != null){
                    nextNode.setPrevious(previousNode);

                }
            }
            else{
                Copy = Copy.clear();
            }
            return Copy;
        }
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        ImmutableLinkedList Copy = CopyOfLinkedList();
        checkBounds(index);
        Copy.NodeWithIndex(index).setElement(e);
        return Copy;
    }

    @Override
    public int indexOf(Object e) {
        Node currentNode = root;
        int counter = 0;
        while (currentNode != null) {
            if(currentNode.getElement().equals(e)){
                return counter;
            }
            currentNode = currentNode.getNext();
            counter++;
        }
        return -1;
    }

    @Override
    public int size() {
        if (getRoot().getElement() == null){
            return 0;
        }
        return indexOf(end.getElement()) + 1;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] Arr = new Object[size()];
        for (int i = 0; i < Arr.length; i++) {
            Arr[i] = NodeWithIndex(i).getElement();
        }
        return  Arr;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node currentNode = root;
        while (currentNode != null && currentNode.getElement() != null) {
            s.append(currentNode.getElement());
            currentNode = currentNode.getNext();
            if (currentNode != null) { s.append(", "); }
        }
        return s.toString();
    }
    private ImmutableLinkedList CopyOfLinkedList(){
        Object[] arr = toArray();
        if(size() > 0) {
            return new ImmutableLinkedList(arr);
        }
        else{
            return new ImmutableLinkedList();
        }
    }
    ImmutableLinkedList addFirst(Object e){
        ImmutableLinkedList Copy = CopyOfLinkedList();
        if(size() > 0) {
            Node newRoot = new Node();
            newRoot.setElement(e);
            Node currentRoot = Copy.root;
            Copy.root = newRoot;
            Copy.root.setNext(currentRoot);
            currentRoot.setPrevious(Copy.root);
        }
        else{
            Node newRoot = new Node();
            newRoot.setElement(e);
            Copy.root = newRoot;
            Copy.end = Copy.root;
        }
        return Copy;
    } //- додає елемент у початок зв'язаного списку
    public ImmutableLinkedList addLast(Object e){
        ImmutableLinkedList Copy = CopyOfLinkedList();
        if (size() > 0) {
            Node nextEl = new Node();
            nextEl.setElement(e);
            nextEl.setPrevious(Copy.end);
            Copy.end.setNext(nextEl);
            Copy.end = Copy.end.getNext();
        }
        else {
            Copy = addFirst(e);
        }
        return Copy;
    } //- додає елемент у кінець зв'язаного списку
    public Object getFirst(){
        return root.getElement();
    }
    public Object getLast(){
        return end.getElement();
    }
    public ImmutableLinkedList removeFirst(){
        ImmutableLinkedList Copy = CopyOfLinkedList();
        if(size() > 1){
            Node NewFirst = Copy.getRoot().getNext();
            NewFirst.setPrevious(null);
            Copy.root = NewFirst;
        }
        else {
            Copy = Copy.clear();
        }
        return Copy ;

    } //- видаляє перший елемент
    public ImmutableLinkedList removeLast(){
        ImmutableLinkedList Copy = CopyOfLinkedList();
        if(size() > 1){
            Copy.end = Copy.end.getPrevious();
            Copy.end.setNext(null);
        }
        else {
            Copy = Copy.clear();
        }
        return Copy;
    } //- видаляє останній елемент

    private void checkBounds(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
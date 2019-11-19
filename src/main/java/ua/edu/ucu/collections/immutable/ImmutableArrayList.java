package ua.edu.ucu.collections.immutable;

public class ImmutableArrayList implements ImmutableList{
    private Object[] list;

    public ImmutableArrayList() {
        list = new Object[0];

    }
    private ImmutableArrayList(int i) {
        list = new Object[i];
    }
    ImmutableArrayList(Object[] el) {
        list = new Object[el.length];
        System.arraycopy(el, 0, list, 0, el.length);
    }
    @Override
    public ImmutableArrayList add(Object e) {
        return add(size(), e);
    }
    @Override
    public ImmutableArrayList add(int index, Object e) {
        Object[] arr = new Object[1];
        arr[0] = e;
        return addAll(index, arr);
    }

    private int Bufsize() {
        int counter = 0;
        for (Object el : list){
            if (el != null){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        checkBounds(index);
        ImmutableArrayList Copy = new ImmutableArrayList(Bufsize() + c.length);
        if (list.length > 0) {
            int i = 0;
            for (int j = 0; j < Copy.list.length; j++) {
                if (j == index) {
                    for (Object el : c) {
                        Copy.list[j] = el;
                        j++;
                    }
                    j--;
                }
                else {
                    Copy.list[j] = list[i];
                    i++;
                }
            }
            return Copy;
        }
        else {
            return new ImmutableArrayList(c);
        }
    }

    @Override
    public Object get(int index) {
        checkBounds(index);
        return list[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        checkBounds(index);
        ImmutableArrayList Copy = new ImmutableArrayList(list.length - 1);
        int c = 0;
        for (int i = 0; i < list.length; i++) {
            if (i != index){
                Copy.list[c] = list[i];
                c++;
            }
        }
        return Copy;
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        checkBounds(index);
        ImmutableArrayList Copy = CopyOfArrayList();
        Copy.list[index] = e;
        return Copy;
    }

    @Override
    public int indexOf(Object e) {
        int counter = 0;
        for (Object el : list) {
            if (el.equals(e)){
                return counter;
            }
            counter++;
        }
        return -1;
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return list;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            s.append(list[i]);
            if (i != list.length - 1) {
                s.append(", ");
            }
        }
        return s.toString();
    }
    private ImmutableArrayList CopyOfArrayList(){
        Object[] arr = toArray();
        return new ImmutableArrayList(arr);
    }

    private void checkBounds(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

}
import java.util.ArrayList;

//Name: Harishkumar Moothedathu
//CWID:10450618
//Class: 570 A {Doubly Linked List using fast access method)
public class IDLList<E> {

    private static class Node<E> {

        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E elem) {
            data = elem;
            this.prev = null;
            this.next = null;
        }

        private Node(E elem, Node<E> prev, Node<E> next) {
            data = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList() {
        size = 0;
        indices = new ArrayList<Node<E>>();
        head = null;
        tail = null;
    }

    public boolean add(int index, E elem) {

        if (index == 0) {
            add(elem);
            return true;
        }

        if (index == size) {
            append(elem);
            return true;
        }

        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Node<E> CNode = indices.get(index);
        Node<E> PNode = indices.get(index - 1);
        Node<E> addNode = new Node<E>(elem, PNode, CNode);
        CNode.prev = addNode;
        PNode.next = addNode;
        size++;
        indices.add(index, addNode);
        return true;

    }

    public boolean add(E elem) {

        Node<E> addNode = new Node<E>(elem);
        if (size == 0) {
            head = addNode;
            tail = addNode;
        } else {
            Node<E> CNode = indices.get(0);
            head = addNode;
            addNode.next = CNode;
            CNode.prev = addNode;
        }
        indices.add(0, addNode);
        size++;
        return true;

    }

    public boolean append(E elem) {

        if (size == 0) {
            add(elem);
            return true;
        }

        Node<E> addNode = new Node<E>(elem);
        Node<E> LNode = indices.get(size - 1);
        LNode.next = addNode;
        addNode.prev = LNode;
        tail = addNode;
        indices.add(size, addNode);
        size++;
        return true;

    }

    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Node<E> getNode = indices.get(index);
        return getNode.data;

    }

    public E getHead() {

        if (size == 0) {
            throw new NullPointerException();
        }

        return head.data;

    }

    public E getLast() {

        if (size == 0) {
            throw new NullPointerException();
        }

        return tail.data;

    }

    public int size() {

        return size;

    }

    public E remove() {

        E data;
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException(size);
        }

        Node<E> removeNode = indices.get(0);
        data = removeNode.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = removeNode.next;
            removeNode.next.prev = null;
        }

        indices.remove(0);
        size--;
        return data;

    }

    public E removeLast() {

        E data;
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException(size);
        }

        if (size == 1) {
            data = remove();
            return data;
        }

        Node<E> removeNode = indices.get(size - 1);
        data = removeNode.data;
        tail = removeNode.prev;
        removeNode.prev.next = null;
        indices.remove(size - 1);
        size--;
        return data;

    }

    public E removeAt(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E data;
        if (index == 0) {
            data = remove();
            return data;
        }

        if (index == (size - 1)) {
            data = removeLast();
            return data;
        }

        Node<E> removeNode = indices.get(index);
        data = removeNode.data;
        Node<E> PNode = indices.get(index - 1);
        Node<E> NNode = indices.get(index + 1);
        PNode.next = NNode;
        NNode.prev = PNode;
        indices.remove(index);
        size--;
        return data;

    }

    public boolean remove(E elem) {

        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException(size);
        }

        E data;
        for (int i = 0; i < size; i++) {
            Node<E> CNode = indices.get(i);
            if (CNode.data == elem) {
                if (i == 0) {
                    data = remove();
                    return true;
                } else if (i == (size - 1)) {
                    data = removeLast();
                    return true;
                } else {
                    data = removeAt(i);
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    public String toString() {

        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException(size);
        }

        String list = "";
        String connect = " \u2b82 ";
        for (int i = 0; i < size; i++) {
            Node<E> CNode = indices.get(i);
            if (i == 0) {
                list = list + CNode.data;
            } else {
                list = list + connect + CNode.data;
            }
        }

        return list;

    }

    public static void main(String[] args) {

    }
}

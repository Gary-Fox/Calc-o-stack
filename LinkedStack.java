import java.util.EmptyStackException;

public class LinkedStack<T> implements StackImplementation<T> {
    private Node topNode;

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

    public LinkedStack() {
        topNode = null;
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();

        T top = topNode.data;
        topNode = topNode.next;
        return top;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();

        return topNode.data;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }
}
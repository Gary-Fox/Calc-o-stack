import java.util.EmptyStackException;

/** Stack data type implemented via linked list
 */
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

    /** Default Constructor
     */
    public LinkedStack() {
        topNode = null;
    }

    /** Pushes an new element onto the stack
     * @param newEntry The entry to be pushed onto the top of the stack
     */
    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    /** Removes and returns the top of the stack
     * @return The element at the top of the stack
     */
    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();

        T top = topNode.data;
        topNode = topNode.next;
        return top;
    }

    /** Returns the top of the stack without removing it from the stack
     * @return The element at the top of the stack
     */
    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();

        return topNode.data;
    }
    
    /** Checks if the stack is empty
     * @return True if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    /** Empties the stack, returns nothing
     */
    @Override
    public void clear() {
        topNode = null;
    }
}
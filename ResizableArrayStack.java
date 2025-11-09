import java.util.Arrays;
import java.util.EmptyStackException;

/** Stack data type implemented via an Array
 */
public class ResizableArrayStack<T> implements StackImplementation<T> {
    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /** Default constructor
     */
    public ResizableArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**Constructor with a specified capacity as a parameter
     * @param initialCapacity the desired capacity for the stack array
     */
    @SuppressWarnings("unchecked")
    public ResizableArrayStack(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Initial capacity must be >= 0");
            
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
    }

    /** Doubles the size of stack capacity
     */
    private void ensureCapacity() {
        if (topIndex >= stack.length - 1) {
            int newLength = 2 * stack.length;
            if (newLength > MAX_CAPACITY)
                throw new RuntimeException("Stack has reached maximum capacity");
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    /** Adds a specified entry to the top of the stack
     * @param newEntry The T type object we want to push to the top of the stack
     */
    @Override
    public void push(T newEntry) {
        ensureCapacity();
        topIndex++;
        stack[topIndex] = newEntry;
    }

    /** Removes the top of the stack and returns it
     * @return The element at the top of the stack
     */
    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
            
        T top = stack[topIndex];
        stack[topIndex] = null;
        topIndex--;
        return top;
    }

    /** Returns the top of the stack, but without removing it
    * @return The element at the top of the stack
    */
    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
            
        return stack[topIndex];
    }

    /** Checks if there are any entries in the stack
     * @return True if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    /** Removes all elements within the stack, returns nothing
     */
    @Override
    public void clear() {
        for (int i = 0; i <= topIndex; i++)
            stack[i] = null;
            
        topIndex = -1;
    }
}
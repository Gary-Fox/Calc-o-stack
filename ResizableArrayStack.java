import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizableArrayStack<T> implements StackImplementation<T> {
    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizableArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ResizableArrayStack(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Initial capacity must be >= 0");
            
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
    }

    private void ensureCapacity() {
        if (topIndex >= stack.length - 1) {
            int newLength = 2 * stack.length;
            if (newLength > MAX_CAPACITY)
                throw new RuntimeException("Stack has reached maximum capacity");
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    @Override
    public void push(T newEntry) {
        ensureCapacity();
        topIndex++;
        stack[topIndex] = newEntry;
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
            
        T top = stack[topIndex];
        stack[topIndex] = null;
        topIndex--;
        return top;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
            
        return stack[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= topIndex; i++)
            stack[i] = null;
            
        topIndex = -1;
    }
}
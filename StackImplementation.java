public interface StackImplementation<T> {
    void push(T newEntry);
    T pop();
    T peek();
    boolean isEmpty();
    void clear();
}
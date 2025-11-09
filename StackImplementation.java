public interface StackImplementation<T> 
{
    /**Pushes a new entry onto the top of this stack.
     * @param newEntry the new entry to be pushed onto this stack.
     */
    void push(T newEntry);
    /**Removes and returns the top entry of this stack.
     * @return whatever was on top of the stack.
     */
    T pop();
    /**Retrieves the top of the stack without removing it.
     * @return
     */
    T peek();
    /**Indicates wether the stack is empty
     * @return True if the stack is empty, false otherwise
     */
    boolean isEmpty();
    /**Empties the stack of all contents
     */
    void clear();
}
package collections.linkedlist;
/**
 * Custom Stack (FIFO)
 * @author Kirill Asmanov
 * @since 05.05.2020
 * @param <T> any type
 */
public class SimpleQueue<T> {
    private SimpleStack<T> in = new SimpleStack<T>();
    private SimpleStack<T> out = new SimpleStack<T>();
    /**
     * Retrieves and removes the tail of this queue.
     * @return tail element
     */
    public T poll() {
        for (T t : in) {
            out.push(in.pop());
        }
        return out.pop();
    }

    /**
     * Adds element on the head of this queue
     * @param value added element
     */
    public void push(T value) {
        in.push(value);
    }
}
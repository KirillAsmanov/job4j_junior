package collections.linkedlist;

import java.util.NoSuchElementException;

/**
 * Custom Stack (FIFO)
 * @author Kirill Asmanov
 * @since 05.05.2020
 * @param <T> any type
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     * @return head element
     */
    public T pop() {
        T firstElement = null;
        try {
            firstElement = linked.getFirst();
            linked.deleteFirst();
        } catch (NoSuchElementException ignored) {
        }
        return firstElement;
    }

    /**
     * Adds element on the head of this queue
     * @param value added element
     */
    public void push(T value) {
        linked.addOnHead(value);
    }
}

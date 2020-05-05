package collections.linkedlist;

import java.util.NoSuchElementException;

/**
 * Custom Stack (LIFO)
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
        T lastElement = null;
        try {
            lastElement = linked.getHead();
            linked.deleteLast();
        } catch (NoSuchElementException ignored) {
        }
        return lastElement;
    }

    /**
     * Adds element on the head of this queue
     * @param value added element
     */
    public void push(T value) {
        linked.addOnHead(value);
    }
}

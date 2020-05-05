package collections.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom Stack (LIFO)
 * @author Kirill Asmanov
 * @since 05.05.2020
 * @param <T> any type
 */
public class SimpleStack<T> implements Iterable<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    /**
     * Retrieves and removes the head of this queue.
     * @return head element
     */
    public T pop() {
        T lastElement = linked.getHead();
        linked.deleteLast();
        return lastElement;
    }

    /**
     * Adds element on the head of this queue
     * @param value added element
     */
    public void push(T value) {
        linked.addOnHead(value);
    }

    @Override
    public Iterator<T> iterator() {
        return linked.iterator();
    }
}

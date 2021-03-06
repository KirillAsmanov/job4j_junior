package collections.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Custom Forward Linked List
 * @author Kirill Asmanov
 * @since 05.05.2020
 * @param <T> any type
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * Contains first node in list
     */
    private Node<T> head;

    /**
     * Adds element on a tail of the list.
     * @param value added element.
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Adds element on the head of the list.
     * @param value added element
     */
    public void addOnHead(T value) {
        if (head == null) {
            head = new Node<T>(value, null);
        } else {
            head = new Node<T>(value, head);
        }
    }

    /**
     * Revert order of elements on list.
     */
    public void revert() {
        for (T input : this) {
            addOnHead(input);
            input = null;
        }
    }

    /**
     * Returns element from the head of the list
     * @return element
     */
    public T getHead() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    /**
     * Deletes last input element in list
     * @return deleted element
     */
    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deleted = head.value;
        if (head.next != null) {
            head = head.next;
        } else {
            head = null;
        }
        return deleted;
    }

    /**
     * Deletes first input element in list
     * @return deleted element
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tail = head;
        while (tail.next.next != null) {
            tail = tail.next;
        }
        T deleted = tail.next.value;
        tail.next = null;
        return deleted;
    }

    /**
     * Creates and returns iterator to bypass this collection.
     * @return iterator of collection
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Node realise
     * @param <T> any type
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

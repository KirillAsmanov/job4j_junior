package collections.linkedlist;

import java.util.*;

/**
 * 2. Создать контейнер на базе связанного списка
 * Custom LinkedList
 * @author Kirill Asmanov
 * @since 03.05.2020
 * @param <E> any type
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    private Node<E> firstNode = new Node<>(null, null, null);
    private Node<E> lastNode = new Node<>(null, null, null);

    private int size = 0;
    private int modCount = 0;

    /**
     * Adds element in the end of the List
     * @param element added element
     */
    public void add(E element) {
        Node<E> newNode;
        if (size == 0) {
            newNode = new Node<E>(null, element, null);
            firstNode.setNext(newNode);
            lastNode.setPrevious(newNode);
        } else {
            newNode = new Node<E>(lastNode.getPrevious(), element, null);
            lastNode.getPrevious().setNext(newNode);
            lastNode.setPrevious(newNode);
        }
        size++;
        modCount++;
    }

    /**
     * Adds element on the index in List
     * @param index index
     * @param element added element
     */
    public void add(int index, E element) {
        if (index == size) {
            add(element);
        } else if (index == 0) {
            Node<E> newNode = new Node<>(null, element, firstNode.getNext());
            newNode.getNext().setPrevious(newNode);
            firstNode.setNext(newNode);
            size++;
            modCount++;
        } else {
            Objects.checkIndex(index, size);
            Node<E> currentNodeOnIndex = findNodeByIndex(index);
            Node<E> newNode = new Node<>(currentNodeOnIndex.getPrevious(), element, currentNodeOnIndex);
            newNode.getPrevious().setNext(newNode);
            newNode.getNext().setPrevious(newNode);
            size++;
            modCount++;
        }
    }

    /**
     * Gets an existing element by index.
     * @param index item index on list
     * @return - (E) item
     * @throws IndexOutOfBoundsException if element with such index doesn't exist
     */
    public E get(int index) {
        return findNodeByIndex(index).getElement();
    }

    /**
     * Sets an existing element by index.
     * @param index item index on list
     * @param element replaceable element
     * @throws IndexOutOfBoundsException if element with such index doesn't exist
     */
    public void set(int index, E element) {
        findNodeByIndex(index).setElement(element);
    }

    /**
     * Remove an existing element by index.
     * @param index item index on list
     * @throws IndexOutOfBoundsException if element with such index doesn't exist
     */
    public void remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<E> removable = findNodeByIndex(index);
            removable.getNext().setPrevious(removable.getPrevious());
            removable.getPrevious().setNext(removable.getNext());
            size--;
            modCount++;
        }
    }

    /**
     * Remove first element on list.
     */
    public void removeFirst() {
        if (firstNode.getNext() == null) {
            throw new NoSuchElementException();
        }
        if (size > 1) {
            firstNode.getNext().getNext().setPrevious(null);
            firstNode.setNext(firstNode.getNext().getNext());
        } else {
            firstNode.setNext(null);
        }
        size--;
        modCount++;
    }

    /**
     * Remove last element on list
     */
    public void removeLast() {
        if (firstNode.getNext() == null) {
            throw new NoSuchElementException();
        }
        if (size > 1) {
            lastNode.getPrevious().getPrevious().setNext(null);
            lastNode.setPrevious(lastNode.getPrevious().getPrevious());
        } else {
            lastNode.setPrevious(firstNode.getNext());
        }
        size--;
        modCount++;
    }

    /**
     * Find existing Node by index
     * @param index Node index
     * @throws IndexOutOfBoundsException if element with such index doesn't exist
     * @return found Node
     */
    private Node<E> findNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        Node<E> found;
        // Оптимизирует количество проходов по элементам, в зависимости от того в первой
        // или второй половине списка ищется индекс
        if (index <= (size - 1) / 2) {
            found = firstNode.getNext();
            for (int i = 0; i < index; i++) {
                found = found.getNext();
            }
        } else {
            found = lastNode.getPrevious();
            int lastIndex = size - 1;
            for (int i = lastIndex; i > index; i--) {
                found = found.getPrevious();
            }
        }
        return found;
    }

    /**
     * Creates and returns iterator to bypass this collection.
     * @return iterator of collection
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> currentNode = firstNode;
            private int currentModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentNode.getNext() != null;
            }

            @Override
            public E next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode.getNext();
                return currentNode.getElement();
            }

            final void checkModCount() {
                if (modCount != currentModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Node realize
     * @param <E> any type
     */
    static class Node<E> {
        private Node<E> next;
        private Node<E> previous;
        private E e;

        Node(Node<E> prev, E element, Node<E> next) {
            this.e = element;
            this.next = next;
            this.previous = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public E getElement() {
            return e;
        }

        public void setElement(E e) {
            this.e = e;
        }
    }
}

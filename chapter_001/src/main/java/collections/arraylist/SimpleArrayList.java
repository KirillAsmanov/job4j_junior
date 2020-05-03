package collections.arraylist;

import java.util.*;
/**
 * 1. Динамический список на массиве. [#281927]
 * Custom ArrayList
 * @author Kirill Asmanov
 * @since 03.05.2020
 * @param <E> any type
 */

public class SimpleArrayList<E> implements Iterable<E> {
    /**
     * Container of data
     */
    private Object[] container = new Object[10];
    /**
     * point on first empty position in array
     */
    private int position = 0;
    /**
     * Contains count of modification entry of list. Used to prevent modification after calling an iterator
     */
    private int modCount = 0;

    /**
     * Adds element on first empty position
     * Expands container capacity x2 when the current container does not have free cells
     * @param element added element
     */
    public void add(E element) {
        this.container[position++] = element;
        if (position == this.container.length) {
            expandContainer();
        }
        modCount++;
    }

    /**
     * Gets an existing element by index.
     * @param index item index on array
     * @return - (E) item
     * @throws IndexOutOfBoundsException if element with such index doesn't exist
     */
    public E get(int index) {
        Objects.checkIndex(index, position);
        return (E) this.container[index];
    }

    /**
     * Sets an existing element by index.
     * @param index item index on array
     * @param element replaceable element
     * @throws IndexOutOfBoundsException if element with such index doesn't exist
     */
    public void set(int index, E element) {
        Objects.checkIndex(index, position);
        this.container[index] = element;
    }

    /**
     * Remove an existing element by index.
     * @param index item index on array
     * @throws IndexOutOfBoundsException if element with such index doesn't exist
     */
    public void remove(int index) {
        Objects.checkIndex(index, position);
        System.arraycopy(this.container, index + 1,
                this.container, index,
                position - index
        );
        this.container[position - 1] = null;
        position--;
        modCount++;
    }

    /**
     * Creates and returns iterator to bypass this collection.
     * @return iterator of collection
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            private int currentModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentIndex < position;
            }

            @Override
            public E next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[currentIndex++];
            }

            final void checkModCount() {
                if (modCount != currentModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Expands container capacity x2 when the current container does not have free cells
     */
    private void expandContainer() {
        if (position >= 0) {
            container = Arrays.copyOf(container, position * 2);
        }
    }
}

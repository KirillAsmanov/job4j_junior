package collections.set;

import generic.SimpleArray;

import java.util.Iterator;

/**
 * 1. Реализовать коллекцию Set на массиве [#281947]
 * Custom Set
 * @author Kirill Asmanov
 * @since 06.05.2020
 * @param <E> any type
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * Array for contain elements
     */
    SimpleArray<E> container;

    /**
     * Constructor
     * @param size length of set
     */
    public SimpleSet(Integer size) {
        this.container = new SimpleArray<>(size);
    }

    /**
     * Adds unique element in set
     * @param value - added element
     * @return true if operation finish successfully / false if it doesn't
     */
    public boolean add(E value) {
        for (E element : container) {
            if (value.equals(element)) {
                return false;
            }
        }
        container.add(value);
        return true;
    }

    /**
     * Iterator realise
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}

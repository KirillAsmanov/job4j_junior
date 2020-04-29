package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1. Что такое итератор.[#281936]
 * @author Kirill Asmanov
 * @since 29.04.2020
 */

public class ArrayIteratorInverse implements Iterator<Integer> {
    private final int[] data;
    private int pointer = 0;

    public ArrayIteratorInverse(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pointer < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - 1 - pointer++];
    }
}

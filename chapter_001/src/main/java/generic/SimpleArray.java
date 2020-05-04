package generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 5.2.1. Реализовать SimpleArray<T> [#281943]
 * Обертка пользовательского массива
 * @author Kirill Asmanov
 * @since 01.05.2020
 * @param <E> any type
 */
public class SimpleArray<E> implements Iterable<E> {
    Object[] data;
    int position = 0;

    public SimpleArray(Integer size) {
        this.data = new Object[size];
    }

    /**
     * Добавляет элемент в первый пустой индекс
     * @param element добавляемый элемент
     */
    public void add(E element) {
        this.data[position++] = element;
    }

    /**
     * Возвращает элемент по указанному индексу если он существует
     * @param index - индекс возвращаемого элемента
     * @return элемент
     * @throws IndexOutOfBoundsException - элемент с данным индексом не добавлен в массив
     */
    public E get(int index) throws IndexOutOfBoundsException {
        return (E) this.data[checkIndex(index)];
    }

    /**
     * Заменяет элемент с данным индексом на переданный элемент
     * @param index - индекс заменяемого элемента
     * @param element - заменяющий элемент
     * @throws IndexOutOfBoundsException - элемент с данным индексом не добавлен в массив
     */
    public void set(int index, E element) throws IndexOutOfBoundsException {
        this.data[checkIndex(index)] = element;
    }

    /**
     * Удаляет элемент с заданным индексом
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException - элемент с данным индексом не добавлен в массив
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        System.arraycopy(this.data,
                index + 1,
                this.data, index,
                position - index
        );
        data[position - 1] = null;
        position--;
    }

    /**
     * Возвращает итератор для обхода массива
     * @return итератор
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < position;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) data[currentIndex++];
            }


        };
    }

    private int checkIndex(int index) throws IndexOutOfBoundsException {
        return Objects.checkIndex(index, position);
    }
}

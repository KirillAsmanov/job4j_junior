package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
/**
 * 5.1.2. Создать итератор четные числа [#281934]
 * @author Kirill Asmanov
 * @since 30.04.2020
 */
public class ArrayIteratorEven implements Iterator<Integer> {
    private final int[] data;
    private int pointer = 0;

    public ArrayIteratorEven(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        Supplier<Boolean> notEven = () -> this.data[pointer] % 2 != 0;
        Supplier<Boolean> pointerIsLast = () -> this.pointer >= this.data.length - 1;

        while (notEven.get() && !pointerIsLast.get()) {
                pointer++;
        }
        return !(pointerIsLast.get() && notEven.get());
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[pointer++];
    }
}

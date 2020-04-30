package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 5.1.1. Итератор для двухмерного массива int[][][#281937]
 * @author Kirill Asmanov
 * @since 30.04.2020
 */
public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int cell = 0;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        Supplier<Boolean> cellIsOutOfRange = () -> this.cell >= this.data[row].length;
        Supplier<Boolean> rowIsLast = () -> this.row == this.data.length - 1;
        Supplier<Boolean> cellsAreEmpty = () -> this.data[row].length == 0;

        while (!rowIsLast.get() && cellsAreEmpty.get()) {
            row++;
        }

        return !(rowIsLast.get() && (cellsAreEmpty.get() || cellIsOutOfRange.get()));
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl;
            // cell is last in row
        if (cell == data[row].length - 1) {
            rsl =  data[row++][cell];
            cell = 0;
        } else {
            rsl =  data[row][cell++];
        }
        return rsl;
    }
}

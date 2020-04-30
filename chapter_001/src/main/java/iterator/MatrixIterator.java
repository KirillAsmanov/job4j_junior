package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
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
        boolean cellIsOutOfRange = cell >= data[row].length;
        boolean rowIsLast = row == data.length - 1;
        boolean cellsAreEmpty = data[row].length == 0;

        return !(rowIsLast && (cellsAreEmpty || cellIsOutOfRange));
    }

    @Override
    public Integer next() {
             // row isn't last AND cells are empty
        while (row != data.length - 1 && data[row].length == 0) {
            row++;
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl;
            // cell is last
        if (cell == data[row].length - 1) {
            rsl =  data[row++][cell];
            cell = 0;
        } else {
            rsl =  data[row][cell++];
        }
        return rsl;
    }
}

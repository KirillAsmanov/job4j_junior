package iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MatrixIteratorTest {
    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIterator it = new MatrixIterator(in);
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIterator it = new MatrixIterator(in);
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIterator it = new MatrixIterator(in);
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIterator it = new MatrixIterator(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIterator it = new MatrixIterator(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIterator it = new MatrixIterator(in);
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIterator it = new MatrixIterator(in);
        it.next();
    }

    @Test
    public void whenMultiHasNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIterator it = new MatrixIterator(in);
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }
}
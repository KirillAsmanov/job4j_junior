package iterator;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArrayIteratorInverseTest {

    @Test
    public void whenMultiCallhasNextThenTrue() {
        ArrayIteratorInverse it = new ArrayIteratorInverse(
                new int[] {1, 2, 3}
        );
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        Iterator<Integer> it = new ArrayIteratorInverse(
                new int[] {1, 2, 3}
        );
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        ArrayIteratorInverse it = new ArrayIteratorInverse(
                new int[] {}
        );
        it.next();
    }
}
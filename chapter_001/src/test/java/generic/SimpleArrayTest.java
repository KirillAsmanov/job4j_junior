package generic;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void createSimpleArrayWithString() {
        SimpleArray<String> simpleArray = new SimpleArray<String>(10);
        simpleArray.add("Я - строка!");
        assertEquals(simpleArray.get(0), "Я - строка!");
    }

    @Test
    public void createSimpleArrayWithBool() {
        SimpleArray<Boolean> simpleArray = new SimpleArray<Boolean>(10);
        simpleArray.add(true);
        assertEquals(simpleArray.get(0), true);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getInvalidIndex() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("first");
        simpleArray.get(1);
    }

    @Test
    public void removeIndex() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("first");
        simpleArray.add("second");
        simpleArray.add("third");
        simpleArray.remove(1);
        assertEquals("third", simpleArray.get(1));
    }

    @Test
    public void setIndex() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("first");
        simpleArray.set(0, "change");
        assertEquals("change", simpleArray.get(0));
    }

    @Test
    public void iteratorHasNext() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("first");
        simpleArray.add("second");
        Iterator<String> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.hasNext(), is(false));
    }
}
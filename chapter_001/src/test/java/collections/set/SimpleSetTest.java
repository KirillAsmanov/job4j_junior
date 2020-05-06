package collections.set;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddsElements() {
        SimpleSet<String> stringSet = new SimpleSet<String>(10);
        assertEquals(stringSet.add("first"), true);
        stringSet.add("second");
        Iterator<String> it = stringSet.iterator();
        assertEquals(it.next(), "first");
        assertEquals(it.hasNext(), true);
        assertEquals(it.next(), "second");
        assertEquals(it.hasNext(), false);
    }

    @Test
    public void whenAddsCopyOfElements() {
        SimpleSet<String> stringSet = new SimpleSet<String>(10);
        stringSet.add("gemini");
        assertEquals(stringSet.add("gemini"), false);
        Iterator<String> it = stringSet.iterator();
        assertEquals(it.next(), "gemini");
        assertEquals(it.hasNext(), false);
    }
}
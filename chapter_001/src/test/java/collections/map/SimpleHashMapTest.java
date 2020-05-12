package collections.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleHashMapTest {
    @Test
    public void whenInsertFewElements() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        User second = new User("second", 1, null);
        assertTrue(simpleHashMap.insert(first, "first"));
        assertTrue(simpleHashMap.insert(second, "second"));
    }

    @Test
    public void whenInsertElementAndReset() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        simpleHashMap.insert(first, "first");
        assertTrue(simpleHashMap.insert(new User("first", 1, null), "CHANGED"));
        assertEquals(simpleHashMap.get(first), "CHANGED");
    }

    @Test
    public void whenInsertFewElementsAndIterateKeys() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        User second = new User("second", 1, null);
        simpleHashMap.insert(first, "first");
        simpleHashMap.insert(second, "second");
        Iterator<User> it = simpleHashMap.keyIterator();
        assertTrue(it.hasNext());
        User next = it.next();
        assertTrue(next.equals(new User("second", 1, null))
                || next.equals(new User("first", 1, null)));
        assertTrue(it.hasNext());
        next = it.next();
        assertTrue(next.equals(new User("second", 1, null))
                || next.equals(new User("first", 1, null)));
        assertFalse(it.hasNext());
    }

    @Test
    public void whenInsertFewElementsAndIterateValues() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        User second = new User("second", 1, null);
        simpleHashMap.insert(first, "first");
        simpleHashMap.insert(second, "second");
        Iterator<String> it = simpleHashMap.valueIterator();
        assertTrue(it.hasNext());
        String next = it.next();
        assertTrue(next.equals("first") || next.equals("second"));
        assertTrue(it.hasNext());
        next = it.next();
        assertTrue(next.equals("first") || next.equals("second"));
        assertFalse(it.hasNext());
    }

    @Test
    public void whenInsertFewElementsAndGet() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        User second = new User("second", 1, null);
        simpleHashMap.insert(first, "first");
        simpleHashMap.insert(second, "second");
        assertEquals(simpleHashMap.get(new User("first", 1, null)), "first");
        assertEquals(simpleHashMap.get(new User("second", 1, null)), "second");
    }

    @Test
    public void whenInsertFewElementsAndGetUnexisted() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        User second = new User("second", 1, null);
        simpleHashMap.insert(first, "first");
        simpleHashMap.insert(second, "second");
        assertNull(simpleHashMap.get(new User("UNEXISTED", 1, null)));
    }

    @Test
    public void whenGetOnEmptyMap() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        assertNull(simpleHashMap.get(new User("first", 1, null)));
    }

    @Test
    public void whenElementDelete() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        User second = new User("second", 1, null);
        simpleHashMap.insert(first, "first");
        simpleHashMap.insert(second, "second");

        assertTrue(simpleHashMap.delete(new User("first", 1, null)));

        Iterator<User> it = simpleHashMap.keyIterator();
        User next = it.next();

        assertEquals(next, new User("second", 1, null));
        assertFalse(it.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenDeleteElementInEmptyMap() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.delete(new User("first", 1, null));
    }

    @Test
    public void whenDeleteUnexistedElement() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User first = new User("first", 1, null);
        simpleHashMap.insert(first, "first");
        assertFalse(simpleHashMap.delete(new User("UNEXISTED", 1, null)));
    }
}
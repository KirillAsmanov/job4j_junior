package collections.linkedlist;

import collections.arraylist.SimpleArrayList;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();

    @Before
    public void setUp() {
        simpleLinkedList.add("zero");
        simpleLinkedList.add("one");
        simpleLinkedList.add("two");
    }

    @Test
    public void whenAddInListTail() {
        simpleLinkedList.add("four");
        String rsl = simpleLinkedList.get(3);
        assertEquals("four", rsl);
    }

    @Test
    public void whenAddInListByIndexInCenter() {
        simpleLinkedList.add(2, "one with half");
        assertEquals("one", simpleLinkedList.get(1));
        assertEquals("one with half", simpleLinkedList.get(2));
        assertEquals("two", simpleLinkedList.get(3));
    }

    @Test
    public void whenAddInListByIndexInFirst() {
        simpleLinkedList.add(0, "zero point five");
        assertEquals("zero point five", simpleLinkedList.get(0));
    }

    @Test
    public void whenGetFirstAndLast() {
        assertEquals("zero", simpleLinkedList.get(0));
        assertEquals("two", simpleLinkedList.get(2));
    }

    @Test
    public void whenSetFirstCenterLast() {
        simpleLinkedList.set(0, "0");
        simpleLinkedList.set(1, "1");
        simpleLinkedList.set(2, "2");
        assertEquals("0", simpleLinkedList.get(0));
        assertEquals("1", simpleLinkedList.get(1));
        assertEquals("2", simpleLinkedList.get(2));
    }

    @Test
    public void whenRemoveFirst() {
        simpleLinkedList.add(0, "delete this");
        simpleLinkedList.remove(0);
        assertEquals("zero", simpleLinkedList.get(0));
    }

    @Test
    public void whenRemoveCenter() {
        simpleLinkedList.add(1, "delete this");
        simpleLinkedList.remove(1);
        assertEquals("one", simpleLinkedList.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenRemoveLast() {
        simpleLinkedList.add("delete this");
        simpleLinkedList.remove(3);
        assertEquals("EXCEPTION", simpleLinkedList.get(3));
    }

    @Test
    public void iteratorHasNextAndDoNext() {
        Iterator<String> it = simpleLinkedList.iterator();
        assertEquals(true, it.hasNext());
        assertEquals("zero", it.next());
        assertEquals(true, it.hasNext());
        assertEquals("one", it.next());
        assertEquals(true, it.hasNext());
        assertEquals("two", it.next());
        assertEquals(false, it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIterator() {
        Iterator<String> it = simpleLinkedList.iterator();
        simpleLinkedList.add("I WILL DROP PROGRAM");
        it.next();
    }
}
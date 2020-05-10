package collections.map;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    private Entry<K, V>[] table;
    private final float loadFactor = 0.75F;
    private Integer size = 0;
    private int modCount = 0;

    /* ------------ public methods ------------ */

    public SimpleHashMap() {
        this.table = new Entry[16];
    }

    /**
     * Insert K-V pair in HashMap, if this pair already exist - change value
     * @param key - K key
     * @param value - V value
     * @return true when insert complete, false when not.
     */
    public boolean insert(K key, V value) {
        if (value == null) {
            return false;
        }
        if (size >= loadFactor * table.length) {
            expandTable();
        }
        int entryHash = hash(key);
        Entry<K, V> foundEntry = getEntry(entryHash);
        if (foundEntry == null) {
            addEntry(entryHash, key, value);
            size++;
            modCount++;
            return true;
        } else if (key.equals(foundEntry.key) && (!value.equals(foundEntry.value))) {
            foundEntry.value = value;
            modCount++;
            return true;
        } else {
            // в случае, если создается коллизия - не обрабатываем, просто возвращаем false.
            return false;
        }

    }

    /**
     * Get value from map by key
     * @param key key
     * @return value in pair
     * @throws NoSuchElementException - when key isn't associated with map
     */
    public V get(K key) {
        int entryHash = hash(key);
        Entry<K, V> foundEntry = getEntry(entryHash);
        if (foundEntry == null) {
            throw new NoSuchElementException();
        }
        return foundEntry.value;
    }

    /**
     * Delete pair key-value from map
     * @param key - key of deleted pair
     * @return true when delete complete, false when it's not
     * @throws NoSuchElementException when map is empty
     */
    public boolean delete(K key) {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int entryHash = hash(key);
        if (getEntry(entryHash) == null) {
            return false;
        } else {
            table[indexFor(entryHash, table.length)] = null;
            size--;
            modCount++;
            return true;
        }
    }

    /* ----------- private methods ------------ */

    /**
     * Adds Entry<K, V> in map
     * @param hash hashcode of key
     * @param key key
     * @param value value
     */
    private void addEntry(int hash, K key, V value) {
        int cell = indexFor(hash, table.length);
        table[cell] = new Entry<>(hash, key, value);
    }

    /**
     * Gets Entry<K, V> from map by hashcode value
     * @param hash hashcode
     * @return entry
     */
    private Entry<K, V> getEntry(int hash) {
        int cell = indexFor(hash, table.length);
        return table[cell];
    }

    /**
     * Calculates the hashcode for key
     * @param key key
     * @return hashcode
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    /**
     * Calculates the number of cell in data-array, which associated with that hashcode
     * @param h hashcode
     * @param length length of current data-array
     * @return number of cell
     */
    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    /**
     * Expands the length of data-array in two times
     */
    private void expandTable() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        for (Entry<K, V> e : oldTable) {
            if (e != null) {
                this.addEntry(e.hash, e.key, e.value);
            }
        }
    }

    /* ------------ inner classes ------------ */

    /**
     * Entry realise
     * @param <K>, <V> -  any type
     */
     static class Entry<K, V> {
        private int hash;
        private K key;
        private V value;

        public Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" + "key=" + key + ", value=" + value + '}';
        }
    }

    /* ------------ iterators ------------ */

    /**
     * Creates and returns entry iterator to bypass this collection.
     * @return entry iterator of collection
     */
    @Override
    public Iterator<SimpleHashMap.Entry<K, V>> iterator() {
        return new Iterator<SimpleHashMap.Entry<K, V>>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                while (pointer < table.length && table[pointer] == null) {
                    pointer++;
                }
                return pointer < table.length;
            }

            @Override
            public SimpleHashMap.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                checkModCount();
                return table[pointer++];
            }

            final void checkModCount() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Creates and returns key iterator to bypass this collection.
     * @return key iterator of collection
     */
    public Iterator<K> keyIterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                while (pointer < table.length && table[pointer] == null) {
                    pointer++;
                }
                return pointer < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                checkModCount();
                return table[pointer++].key;
            }

            final void checkModCount() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Creates and returns value iterator to bypass this collection.
     * @return value iterator of collection
     */
    public Iterator<V> valueIterator() {
        return new Iterator<V>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                while (pointer < table.length && table[pointer] == null) {
                    pointer++;
                }
                return pointer < table.length;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                checkModCount();
                return table[pointer++].value;
            }

            final void checkModCount() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}

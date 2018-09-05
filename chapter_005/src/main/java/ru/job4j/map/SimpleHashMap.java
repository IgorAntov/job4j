package ru.job4j.map;

import java.util.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable<K> {

    private int count;
    private static final int CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table = new Node[CAPACITY];

    /**Entry to Array
     * @param <K> - key
     * @param <V> - value
     */
    static class Node<K, V> {
        final K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }
    }

    /**
     * Method evaluates hash for key
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    /**
     * Method inserts new Entry (key, value) to array
     * @param key
     * @param value
     * @return
     */
    boolean insert(K key, V value) {
        boolean result;
        int index = (table.length - 1) & hash(key);
       // int index = hash(key);
        Node<K, V> p = table[index];
        if (p == null) {
            table[index] = new Node(key, value);
            this.count++;
            if (count > (table.length - 1) * LOAD_FACTOR) {
                table = resize(table.length);
            }
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    /** Method gets entry - Node from Array
     * @param key
     * @return
     */
    public V get(K key) {
        V result = null;
        if (key != null) {
            if (table[hash(key)] != null) {
                result = table[hash(key)].getValue();
            }
        }
        return result;
    }

    /** Method deletes entry from Array
     * @param key
     * @return
     */
    boolean delete(K key) {
        boolean result = false;
        if (key != null) {
            if (table[hash(key)] != null) {
                table[hash(key)] = null;
                this.count--;
                result = true;
            }
        }
        return result;
    }

    /**
     * Method resize capacity (double capacity) of array
     * @param - old array length
     * @return
     */
    private Node<K, V>[] resize(int length) {
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[length * 2];
        Node<K, V> e;
        for (int j = 0; j < table.length; j++) {
            e = table[j];
            if (e != null) {
                if (newTab[hash(e.key)] == null) {
                    newTab[hash(e.key)] = e;

                } else {
                    newTab = resize(newTab.length);
                    break;
                }
            }
        }
        return newTab;
    }

    /**
     *  Method returns entries amount in the array
     */
    public int size() {
        return this.count;
    }

    /**
     *Method implements iterable interface
     * @return key iterator
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = count;
            int indexIterator = 0;
            int pointer = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != count) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (indexIterator < count) {
                    result = true;
                }
                return result;
            }

            @Override
            public K next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = null;
                while (pointer < table.length) {
                    if (table[pointer] != null) {
                        key = table[pointer++].getKey();
                        indexIterator++;
                        break;
                    } else {
                        pointer++;
                    }
                }
                return key;
            }
        };
    }
}

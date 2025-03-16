package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count == (int) (capacity * LOAD_FACTOR)) {
            expand();
        }
        int index = getIndexByKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = getIndexByKey(key);
        MapEntry<K, V> p = table[index];
        if (p != null) {
            if (checkTheEqualityOfKeys(p.key, key)) {
                rsl = p.value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = getIndexByKey(key);
        MapEntry<K, V> p = table[index];
        if (p != null) {
            if (checkTheEqualityOfKeys(p.key, key)) {
                p.key = null;
                p.value = null;
                p = null;
                table[index] = null;
                rsl = true;
                count--;
                modCount++;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private int index = 0;
            private final int expectedModCount = NonCollisionMap.this.modCount;

            @Override
            public boolean hasNext() {
                if (NonCollisionMap.this.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[index] == null) {
                    index++;
                }
                point++;
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int getIndexByKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean checkTheEqualityOfKeys(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2)
                && Objects.equals(key1, key2);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> element : table) {
            if (element != null) {
                newTable[indexFor(hash(Objects.hashCode(element.key)))] = element;
            }
        }
        table = newTable;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, Integer> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
    }
}
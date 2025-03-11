package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);
    private int modCount;

    @Override
    public boolean add(T value) {
        boolean rsl = true;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                rsl = false;
                break;
            }
        }
        if (rsl) {
            set.add(value);
            modCount++;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private final int expectedModCount = SimpleArraySet.this.modCount;

            @Override
            public boolean hasNext() {
                if (SimpleArraySet.this.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point < set.size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(point++);
            }
        };
    }
}
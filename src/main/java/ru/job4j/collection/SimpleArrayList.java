package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T oldValue = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private final int expectedModCount = SimpleArrayList.this.modCount;

            @Override
            public boolean hasNext() {
                if (SimpleArrayList.this.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return SimpleArrayList.this.container[point++];
            }
        };
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2 + 1);
    }
}

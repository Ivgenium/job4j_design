package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        final ForwardLinked.Node<T> newNode = new ForwardLinked.Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            ForwardLinked.Node<T> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> rsl = head;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> temp = head;
        head = temp.next;
        T rsl = temp.item;
        temp.item = null;
        temp.next = null;
        temp = null;
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private ForwardLinked.Node<T> node = ForwardLinked.this.head;
            private final int expectedModCount = ForwardLinked.this.modCount;

            @Override
            public boolean hasNext() {
                if (ForwardLinked.this.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ForwardLinked.Node<T> currentNode = node;
                node = node.next;
                return currentNode.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private ForwardLinked.Node<E> next;

        Node(E element, ForwardLinked.Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
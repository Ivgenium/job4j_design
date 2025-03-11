package ru.job4j.set;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAddNonNullString() {
        SimpleSet<String> set = new SimpleArraySet<>();
        assertThat(set.add("A")).isTrue();
        assertThat(set.add("B")).isTrue();
        assertThat(set.add("C")).isTrue();
        assertThat(set.contains("A")).isTrue();
        assertThat(set.contains("B")).isTrue();
        assertThat(set.contains("D")).isFalse();
        assertThat(set.add("A")).isFalse();
    }

    @Test
    void whenGetIteratorFromEmptySetThenNextThrowException() {
        SimpleSet<String> set = new SimpleArraySet<>();
        assertThatThrownBy(set.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenAddAfterGetIteratorHasNextThenMustBeException() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        set.add(3);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }
}
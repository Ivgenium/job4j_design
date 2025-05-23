package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 5, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemove() {
        List<Integer> inputTest = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeIf(inputTest, el -> el % 2 == 0);
        assertThat(inputTest).hasSize(3).containsSequence(1, 3, 5);
    }

    @Test
    void whenReplace() {
        List<Integer> inputTest = new ArrayList<>(Arrays.asList(1, 3, 5));
        ListUtils.replaceIf(inputTest, el -> el % 2 != 0, 0);
        assertThat(inputTest).hasSize(3).containsSequence(0, 0, 0);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> inputTest = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeAll(inputTest, new ArrayList<>(Arrays.asList(1, 3, 5)));
        assertThat(inputTest).hasSize(3).containsSequence(2, 4, 6);
    }
}
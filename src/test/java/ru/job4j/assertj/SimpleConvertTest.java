package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkAnotherArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("January", "February", "March");
        assertThat(array).doesNotContainNull()
                .doesNotContain("April")
                .contains("February", Index.atIndex(1))
                .containsAnyOf("March", "April", "May")
                .isEqualTo(new String[] {"January", "February", "March"});
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("January", "February", "March");
        assertThat(list).doesNotContainNull()
                .doesNotContain("April")
                .contains("February", Index.atIndex(1))
                .containsAnyOf("March", "April", "May")
                .isEqualTo(Arrays.asList(new String[] {"January", "February", "March"}));
    }

    @Test
    void checkAnotherList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("-15", "-10", "-5");
        assertThat(list).doesNotContainNull()
                .allSatisfy(e -> assertThat(Integer.valueOf(e)).isNegative())
                .doesNotContain("0")
                .hasSize(3)
                .isEqualTo(List.of("-15", "-10", "-5"));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("north", "south", "west",  "east");
        assertThat(set).isNotEmpty()
                .anySatisfy(e -> assertThat("Northeast").contains(e))
                .anySatisfy(e -> assertThat("Südsüdwest").contains(e))
                .doesNotContain("Northeast")
                .noneMatch(e -> e.equals("nord"));

    }

    @Test
    void checkAnotherSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("rock", "paper", "scissors");
        assertThat(set).isNotEmpty()
                .anySatisfy(e -> assertThat("I wanna rock..").contains(e))
                .anySatisfy(e -> assertThat("paper clip").isNotEqualTo(e))
                .doesNotContain("tomato")
                .noneMatch(e -> e.equals("knife"));

    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("Monday", "Tuesday", "Wednesday");
        assertThat(map).hasSize(3)
                .containsEntry("Wednesday", 2)
                .containsKey("Monday")
                .containsValue(1)
                .doesNotContainValue(3);
    }
}
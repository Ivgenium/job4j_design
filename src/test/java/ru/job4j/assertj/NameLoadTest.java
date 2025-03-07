package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void wheNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void wheDoesNotContainTheSymbolEqually() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Monday=Понедельник",
                "Tuesday=Вторник",
                "Wednesday:Среда")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: Wednesday:Среда does not contain the symbol '='");
    }

    @Test
    void wheDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Monday=Понедельник",
                "=Вторник",
                "Wednesday=Среда")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =Вторник does not contain a key");
    }

    @Test
    void wheDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Monday=",
                "Tuesday=Вторник",
                "Wednesday:Среда")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: Monday= does not contain a value");
    }
}
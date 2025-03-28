package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairsWithCommentsAndEmptyLines() {
        String path = "./data/pairs_with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("database.username")).isEqualTo("test");
        assertThat(config.value("database.password")).isEqualTo("password");
        assertThat(config.value("server.host")).isEqualTo("127.0.0.1");
        assertThat(config.value("server.port")).isEqualTo("8080");
    }

    @Test
    void whenDoesNotContainAValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Line: \"database.username=\" does not contain a key-value pair");
    }

    @Test
    void whenDoesNotContainAKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Line: \"8080\" does not contain a key-value pair");
    }
}
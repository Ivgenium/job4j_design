package ru.job4j.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PhoneNumberTest {
    private final List<PhoneNumber> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        list.add(new PhoneNumber(905, 654, 7842));
        list.add(new PhoneNumber(906, 125, 3389));
    }

    @Test
    void whenTheSameHashCode() {
        PhoneNumber test = new PhoneNumber(905, 654, 7842);
        assertThat(list.get(0).hashCode()).isEqualTo(test.hashCode());
    }

    @Test
    void whenTheSameAnotherHashCode() {
        int expectedHashCode = Short.hashCode(list.get(1).getAreaCode());
        expectedHashCode = 31 * expectedHashCode + Short.hashCode(list.get(1).getPrefix());
        expectedHashCode = 31 * expectedHashCode + Short.hashCode(list.get(1).getLineNum());
        assertThat(list.get(1).hashCode()).isEqualTo(expectedHashCode);
    }
}
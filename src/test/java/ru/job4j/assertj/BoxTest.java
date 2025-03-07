package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 5);
        String name = box.whatsThis();
        assertThat(name).contains("Tetra")
                .doesNotContain("Cu")
                .isNotEqualTo("Sphere")
                .isNotEqualTo("Cube")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name).contains("Cu")
                .doesNotContain("Tet")
                .isNotEqualTo("Sphere")
                .isNotEqualTo("Tetrahedron")
                .isEqualTo("Cube");
    }

    @Test
    void whenGetNumberOfVerticesThanEight() {
        Box box = new Box(8, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotNegative()
                .isPositive()
                .isLessThan(9)
                .isGreaterThan(7)
                .isEqualTo(8);
    }

    @Test
    void whenGetNumberOfVerticesThanZero() {
        Box box = new Box(0, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotNegative()
                .isNotPositive()
                .isLessThan(1)
                .isGreaterThan(-1)
                .isZero();
    }

    @Test
    void whenIsExistThanTrue() {
        Box box = new Box(4, 5);
        boolean rsl = box.isExist();
        assertThat(rsl).isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void whenIsExistThanFalse() {
        Box box = new Box(-5, 5);
        boolean rsl = box.isExist();
        assertThat(rsl).isNotEqualTo(true)
                .isFalse();
    }

    @Test
    void whenGetAreaOfSphere() {
        Box box = new Box(0, 5);
        double rsl = box.getArea();
        assertThat(rsl).isNotNegative()
                .isNotZero()
                .isPositive()
                .isGreaterThan(314.1d)
                .isLessThan(314.2d)
                .isCloseTo(314.1d, withPrecision(0.06));
    }

    @Test
    void whenGetAreaOfTetrahedron() {
        Box box = new Box(4, 5);
        double rsl = box.getArea();
        assertThat(rsl).isNotNegative()
                .isNotZero()
                .isPositive()
                .isGreaterThan(43.3d)
                .isLessThan(43.4d)
                .isCloseTo(43.4d, Percentage.withPercentage(0.23d));
    }

    @Test
    void whenGetAreaOfCube() {
        Box box = new Box(8, 5);
        double rsl = box.getArea();
        assertThat(rsl).isNotNegative()
                .isNotZero()
                .isPositive()
                .isGreaterThan(149.0d)
                .isLessThan(151.0d)
                .isCloseTo(150.0d, Percentage.withPercentage(0.001d));
    }
}
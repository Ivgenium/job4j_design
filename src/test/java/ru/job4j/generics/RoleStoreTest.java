package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsDirector() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        Role result = roleStore.findById("5");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsDirector() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        roleStore.add(new Role("1", "Producer"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenReplaceThenRoleNameIsProducer() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        roleStore.replace("1", new Role("1", "Producer"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Producer");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        roleStore.replace("10", new Role("10", "Producer"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        roleStore.delete("1");
        Role rsl = roleStore.findById("1");
        assertThat(rsl).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsDirector() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        roleStore.delete("5");
        Role rsl = roleStore.findById("1");
        assertThat(rsl.getRoleName()).isEqualTo("Director");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        boolean result = roleStore.replace("1", new Role("1", "Producer"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Director"));
        boolean result = roleStore.replace("5", new Role("5", "Producer"));
        assertThat(result).isFalse();
    }

}
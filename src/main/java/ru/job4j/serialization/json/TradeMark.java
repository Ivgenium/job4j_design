package ru.job4j.serialization.json;

public class TradeMark {
    private final String name;

    public TradeMark(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TradeMark{"
                + "name='" + name + '\''
                + '}';
    }
}

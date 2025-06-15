package ru.job4j.serialization.json;

import java.util.Arrays;

public class Nomenclature {
    private final int id;
    private final String name;
    private final boolean used;
    private final String[] colours;
    private final TradeMark tradeMark;

    public Nomenclature(int id, String name, TradeMark tradeMark, String[] colours, boolean used) {
        this.id = id;
        this.name = name;
        this.tradeMark = tradeMark;
        this.colours = colours;
        this.used = used;
    }

    @Override
    public String toString() {
        return "Nomenclature{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", used=" + used
                + ", colours=" + Arrays.toString(colours)
                + ", tradeMark=" + tradeMark
                + '}';
    }
}

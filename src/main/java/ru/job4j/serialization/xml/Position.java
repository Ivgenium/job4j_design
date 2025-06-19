package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "position")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position {
    private String name;

    public Position() { }

    public Position(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{"
                + "name='" + name + '\''
                + '}';
    }
}

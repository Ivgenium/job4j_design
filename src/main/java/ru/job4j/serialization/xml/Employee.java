package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private int id;
    private String name;
    @XmlElement(name = "position")
    private Position position;
    @XmlElementWrapper(name = "contacts")
    @XmlElement(name = "contact")
    private Contact[] contacts;
    private boolean valid;

    public Employee() { }

    public Employee(int id, String name, Position position, Contact[] contacts, boolean valid) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.contacts = contacts;
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", position=" + position
                + ", contacts=" + Arrays.toString(contacts)
                + ", valid=" + valid
                + '}';
    }
}

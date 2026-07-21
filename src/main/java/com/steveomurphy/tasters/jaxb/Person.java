package com.steveomurphy.tasters.jaxb;
import java.util.Objects;


// Whether fields or Javabean properties are serialized by default
import jakarta.xml.bind.annotation.XmlAccessorType;
//Controls serialization of fields or properties
import jakarta.xml.bind.annotation.XmlAccessType;
// Maps class to XML element
import jakarta.xml.bind.annotation.XmlRootElement;

// TODO summarize these
// Says the Person class is an XML element and tha tits fields are to be serialized
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    private String name;
    private int age;
    private int id;

    // Required by JAXB
    public Person() {
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {

        return "Person{name='" + name + "', age=" + age +  ", id=" + id + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return age == person.age
                && id == person.id
                && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, id);
    }


}
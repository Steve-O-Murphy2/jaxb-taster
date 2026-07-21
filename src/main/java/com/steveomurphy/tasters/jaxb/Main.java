package com.steveomurphy.tasters.jaxb;

// Packages needed for marshalling and unmarshalling
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        Person person = new Person("Stevie", 42, 22134);

        // Create JAXB context
        JAXBContext context = JAXBContext.newInstance(Person.class);

        // Marshall the Java object (Java -> XML) and print.
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(person, writer);

        String xml = writer.toString();

        System.out.println("XML:");
        System.out.println(xml);

        // Unmarshall the XML (XML -> Java)
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Gotta cast to Person class and print using the toString method
        Person restored =
                (Person) unmarshaller.unmarshal(new StringReader(xml));

        System.out.println("\nObject:");
        System.out.println(restored);
    }
}
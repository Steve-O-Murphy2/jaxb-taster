// 1. Import the necessary Jupiter APIs
import com.steveomurphy.tasters.jaxb.Person;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {


    @Test
    @DisplayName("Should successfully prove that the Person object was marshalled into XML.")
    void testMarshalling() throws Exception{
        Person person = new Person("Sunil", 30, 87789);
        JAXBContext context = JAXBContext.newInstance(Person.class);

        // Marshall the Java object (Java -> XML) and print.
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(person, writer);
        String xml = writer.toString();

        assertTrue(xml.contains("<person>"));
        assertTrue(xml.contains("<name>Sunil</name>"));
        assertTrue(xml.contains("<age>30</age>"));
        assertTrue(xml.contains("<id>87789</id>"));
    }

    @Test
    @DisplayName("Should show that the marshalled original person is the same as the unmarshalled restored person")
    void testRoundTrip() throws Exception{
        Person original = new Person("Sunil", 30, 87789);
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Marshall the Java object (Java -> XML) and print.
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(original, writer);

        String xml = writer.toString();
        // Create the restored Person by unmarshalling the original.
        Person restored = (Person) unmarshaller.unmarshal(new StringReader(xml));
        assertEquals(original, restored);

    }
}
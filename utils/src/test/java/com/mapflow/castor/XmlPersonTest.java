package com.mapflow.castor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.exolab.castor.mapping.FieldHandlerFactory;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import com.mapflow.model.Person;
import com.mapflow.util.file.FileUtil;

public class XmlPersonTest extends TestCase {

  private void printPersonInfo(final Person person) {

    System.out.println("Person Attributes");
    System.out.println("-----------------");
    System.out.println("Name: " + person.getName());
    System.out.println("Address: " + person.getAddress());
    System.out.println("SSN: " + person.getSsn());
    System.out.println("Email: " + person.getEmail());
    System.out.println("Home Phone: " + person.getHomePhone());
    System.out.println("Work Phone: " + person.getWorkPhone());
  }

  public void testCreatePerson() throws MarshalException, ValidationException, IOException {
    // -- create a person to work with
    final Person person =
      new Person("Bob Harris", "123 Foo Street", "222-222-2222", "bob@harris.org",
        "(123) 123-1234", "(123) 123-1234");

    // -- marshal the person object out as a <person>
    final FileWriter file = new FileWriter("target/bob_person.xml");
    Marshaller.marshal(person, file);

    file.close();

    final Person personRetrieved =
      (Person) Unmarshaller.unmarshal(Person.class, new FileReader("target/bob_person.xml"));

    assertEquals(person, personRetrieved);
  }

  public void testCreatePersonList() throws MarshalException, ValidationException, IOException {

    // -- create a person to work with
    final Person person1 =
      new Person("Bob Harris", "123 Foo Street", "222-222-2222", "bob@harris.org",
        "(123) 123-1234", "(123) 123-1234");

    final Person person2 =
      new Person("David Jimenez", "10 Fowley Street", "333-222-2222", "david.jimenez@mapflow.com",
        "(123) 123-1234", "(123) 123-1234");

    final List<Person> persons = new ArrayList<Person>();

    persons.add(person1);
    persons.add(person2);

    // -- marshal the person object out as a <person>
    final FileWriter file = new FileWriter("target/person_list.xml");
    Marshaller.marshal(persons, file);

    file.close();

    final Person personRetrieved =
      (Person) Unmarshaller.unmarshal(Person.class, new FileReader("target/bob_person.xml"));

    assertEquals(person1, personRetrieved);
  }

  public void testCreatePersonWithMapping() throws MarshalException, ValidationException, IOException {
    
    // -- create a person to work with
    final Person person =
      new Person("Bob Harris", "123 Foo Street", "222-222-2222", "bob@harris.org",
        "(123) 123-1234", "(123) 123-1234");

    // -- marshal the person object out as a <person>
    final FileWriter file = new FileWriter("target/bob_person_mapping.xml");
    
    
    final Mapping mapping = ... ;
    final FieldHandlerFactory factory = ...;
    final Marshaller m = new Marshaller(file);
    final ClassDescriptorResolverImpl cdr = new ClassDescriptorResolverImpl();
    cdr.getIntrospector().addFieldHandlerFactory(factory);
    m.setResolver(cdr);
    m.setMapping(mapping);
    
    
    Marshaller.marshal(person, file);
   
    file.close();

    final Person personRetrieved =
      (Person) Unmarshaller.unmarshal(Person.class, new FileReader("target/bob_person.xml"));

    assertEquals(person, personRetrieved);
  }

  public void testModifyPerson() throws MarshalException, ValidationException, IOException {

    FileUtil.delete("target/person.xml");

    FileUtil.copy("src/test/resources/person-data.xml", "target/person.xml");

    final Person person =
      (Person) Unmarshaller.unmarshal(Person.class, new FileReader("target/person.xml"));
    // -- change the name
    person.setName("David Beckham");

    // -- marshal the changed person back to disk
    final FileWriter file = new FileWriter("target/personModified.xml");
    Marshaller.marshal(person, file);

    file.close();
  }

  public void testReadPerson() throws MarshalException, ValidationException, FileNotFoundException {

    final Person person =
      (Person) Unmarshaller.unmarshal(Person.class, new FileReader(
        "src/test/resources/person-data.xml"));

    printPersonInfo(person);
  }

  @SuppressWarnings("unchecked")
  public void testReadPersonList() throws MarshalException, ValidationException, IOException {

    List<Person> persons = new ArrayList<Person>();

    persons =
      (List<Person>) Unmarshaller.unmarshal(persons.getClass(), new FileReader(
        "src/test/resources/person_list.xml"));

    System.out.println("\nPrinting person's list\n");

    for (final Person person : persons) {
      printPersonInfo(person);
    }
  }
}

package br.com.thiago.services;

import br.com.thiago.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    // para simular id
    private final AtomicLong counter = new AtomicLong();

    // mostrar no console
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        //mostra no console
        logger.info("Buscando uma pessoa");

        Person person = new Person();

        // Mock:
        person.setId(counter.incrementAndGet());
        person.setFirstName("Thiago");
        person.setLastName("Carretero");
        person.setAddress("Santo Antônio do Jardim");
        person.setGender("Masculino");

        return person;
    }

    public List<Person> findAll() {
        //mostra no console
        logger.info("Buscando uma pessoa - findAll");
        List<Person> personList = new ArrayList<>();

        // Mock: de 7 Person
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            personList.add(person);
        }
        return personList;
    }

    public Person create(Person person) {
        //mostra no console
        logger.info("Cria uma Person");

        //Mock:

        return person;
    }

    public Person update(Person person) {
        //mostra no console
        logger.info("Cria uma Person");

        //Mock:

        return person;
    }

    public void delete(String id) {
        //mostra no console
        logger.info("Cria uma Person");
    }

    private Person mockPerson(int i) {
        // Mock:
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Nome " + i);
        person.setLastName("Sobrenome " + i);
        person.setAddress("Brasil - " + i);
        person.setGender("Masculino" + i);

        return person;
    }
}

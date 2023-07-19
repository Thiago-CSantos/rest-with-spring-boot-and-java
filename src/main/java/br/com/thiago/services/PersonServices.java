package br.com.thiago.services;

import br.com.thiago.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    // para simular id
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Buscando uma pessoa");

        Person person =  new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Thiago");
        person.setLastName("Carretero");
        person.setAddress("Santo Antônio do Jardim");
        person.setGender("Masculino");

        return person;
    }

}

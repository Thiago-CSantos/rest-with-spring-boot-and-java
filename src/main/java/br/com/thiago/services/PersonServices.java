package br.com.thiago.services;

import br.com.thiago.model.Person;
import br.com.thiago.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;


    // mostrar no console
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(Long id) {
        //mostra no console
        logger.info("Buscando uma pessoa");

        return repository.findById(id).orElseThrow();

    }

    public List<Person> findAll() {
        //mostra no console
        logger.info("Buscando uma pessoa - findAll");
        return repository.findAll();
    }

    public Person create(Person person) {
        //mostra no console
        logger.info("Cria uma Person");

        return repository.save(person);
    }

    public Person update(Person person) {
        //mostra no console
        logger.info("Atualiza uma Person");

        Person entity = repository.findById(person.getId()).orElseThrow();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        //mostra no console
        logger.info("deleta uma Person");
        Person entity = repository.findById(id).orElseThrow();

        repository.delete(entity);
    }

}

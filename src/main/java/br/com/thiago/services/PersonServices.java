package br.com.thiago.services;

import br.com.thiago.dto.PersonVo;
import br.com.thiago.dto.PersonVoResponse;
import br.com.thiago.model.Person;
import br.com.thiago.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
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

    public PersonVoResponse findById(Long id) {
        //mostra no console
        logger.info("Buscando uma pessoa");

        var entity = repository.findById(id);

        // verifica se a entitidade existe
        if(entity.isPresent()){
            PersonVoResponse personVo = new PersonVoResponse();

            BeanUtils.copyProperties(personVo, entity);
            return personVo;
        }

        return null;

    }

    public List<PersonVo> findAll() {
        //mostra no console
        logger.info("Buscando uma pessoa - findAll");
        List<PersonVo> personVoList = repository.findAll().stream().map((Person p) -> new PersonVo(
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getAddress(),
                p.getGender())
        ).toList();
        // ou List<PersonVo> personVoList = repository.findAll().stream().map(PersonVo::new).toList();
        return personVoList;
    }

    public Person create(PersonVo personVo) {
        //mostra no console
        logger.info("Cria uma Person");

        Person person = new Person();
        // Converte o personVo para person
        BeanUtils.copyProperties(personVo, person);

        return repository.save(person);


    }

    public Person update(PersonVo person) {
        //mostra no console
        logger.info("Atualiza uma Person");

        Person entity = repository.findById(person.id()).orElseThrow();

        entity.setFirstName(person.firstName());
        entity.setLastName(person.lastName());
        entity.setAddress(person.address());
        entity.setGender(person.gender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        //mostra no console
        logger.info("deleta uma Person");
        var entity = repository.findById(id).orElseThrow();

        repository.delete(entity);
    }

}

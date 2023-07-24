package br.com.thiago.services;

import br.com.thiago.mapper.DozerMapper;
import br.com.thiago.model.Person;
import br.com.thiago.repository.PersonRepository;
import br.com.thiago.vo.v1.PersonVo;
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

    public PersonVo findById(Long id) {
        //mostra no console
        logger.info("Buscando uma pessoa");

        var entity = repository.findById(id);

        return DozerMapper.parseObject(entity, PersonVo.class);

    }

    public List<PersonVo> findAll() {
        //mostra no console
        logger.info("Buscando uma pessoa - findAll");
        return DozerMapper.parseListObject(repository.findAll(), PersonVo.class);
    }

    public PersonVo create(PersonVo person) {
        //mostra no console
        logger.info("Cria uma Person");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVo.class);

        return vo;
    }

    public PersonVo update(PersonVo person) {
        //mostra no console
        logger.info("Atualiza uma Person");

        Person entity = repository.findById(person.getId()).orElseThrow();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVo.class);

        return vo;
    }

    public void delete(Long id) {
        //mostra no console
        logger.info("deleta uma Person");
        var entity = repository.findById(id).orElseThrow();

        repository.delete(entity);
    }

}

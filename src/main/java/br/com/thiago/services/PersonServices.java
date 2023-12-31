package br.com.thiago.services;

import br.com.thiago.controller.PersonController;
import br.com.thiago.dto.PersonVo;
import br.com.thiago.model.Person;
import br.com.thiago.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PagedResourcesAssembler<Person> assembler;

    // mostrar no console
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonVo findById(Long id) {
        //mostra no console
        logger.info("Buscando uma pessoa");

        Person entity = repository.findById(id).orElseThrow(() -> new RuntimeException("NÃO ENCONTRADO"));

        PersonVo personVo = new PersonVo(entity);

        BeanUtils.copyProperties(personVo, entity);
        // Esse "findById" é o nome do metodo no controller
        personVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVo;

    }

    @Transactional
    public PersonVo disablePerson(Long id) {
        //mostra no console
        logger.info("Desabilitando uma pessoa");
        repository.disablePerson(id);

        Person entity = repository.findById(id).orElseThrow(() -> new RuntimeException("DESABILITADO"));

        PersonVo personVo = new PersonVo(entity);

        BeanUtils.copyProperties(personVo, entity);
        // Esse "findById" é o nome do metodo no controller
        personVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVo;

    }

    @Transactional
    public PersonVo enablePerson(Long id) {
        //mostra no console
        logger.info("Habilitar uma pessoa");
        repository.enabledPerson(id);

        Person entity = repository.findById(id).orElseThrow(() -> new RuntimeException("HABILITADO"));

        PersonVo personVo = new PersonVo(entity);

        BeanUtils.copyProperties(personVo, entity);
        // Esse "findById" é o nome do metodo no controller
        personVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVo;
    }

    public PagedModel<EntityModel<Person>> findAll(Pageable pageable) {
        //mostra no console
        logger.info("Buscando uma pessoa - findAll");

        var personPage = repository.findAll(pageable);

        //var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, PersonVo.class));

        List<PersonVo> personVoList = repository.findAll(pageable).stream().map((p) -> new PersonVo(
                        p.getId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getAddress(),
                        p.getGender(),
                        p.getEnabled()
                )
        ).toList();
        // ou List<PersonVo> personVoList = repository.findAll().stream().map(PersonVo::new).toList();

        personPage.map(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));

        Link link = linkTo(methodOn(PersonController.class).people(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

        return assembler.toModel(personPage, link);
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

        Person entity = repository.findById(person.getId_chave()).orElseThrow();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        //mostra no console
        logger.info("deleta uma Person");
        var entity = repository.findById(id).orElseThrow();

        repository.delete(entity);
    }


    public PagedModel<EntityModel<Person>> findPersonsByNames(String firstname, Pageable pageable) {
        //mostra no console
        logger.info("Buscando uma pessoa - findPersonsByNames");

        var personPage = repository.findPersonsByNames(firstname, pageable);

        personPage.map(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));

        Link link = linkTo(methodOn(PersonController.class)
                .people(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

        return assembler.toModel(personPage, link);
    }
}

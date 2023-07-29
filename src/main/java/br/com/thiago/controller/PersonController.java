package br.com.thiago.controller;

import br.com.thiago.dto.PersonVo;
import br.com.thiago.model.Person;
import br.com.thiago.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/person")
public class PersonController {

    @Autowired
    private PersonServices services;
    //private PersonServices services = new PersonServices();

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonVo> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PersonVo>> people() {
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Person> create(@RequestBody PersonVo person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.create(person));
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Person> update(@RequestBody PersonVo person) {
        return ResponseEntity.status(HttpStatus.OK).body(services.update(person));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}

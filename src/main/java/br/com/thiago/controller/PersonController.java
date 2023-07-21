package br.com.thiago.controller;

import br.com.thiago.model.Person;
import br.com.thiago.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;
    //private PersonServices services = new PersonServices();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") String id) throws Exception {
        return services.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> people() {
        return services.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Person create(@RequestBody Person person) {
        return services.create(person);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Person update(@RequestBody Person person) {
        return services.update(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        services.delete(id);
    }

}

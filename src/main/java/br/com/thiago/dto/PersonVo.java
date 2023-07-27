package br.com.thiago.dto;

import br.com.thiago.model.Person;


public record PersonVo(Long id, String firstName, String lastName, String address, String gender) {

    public PersonVo(Person person) {
        this(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender());
    }

}

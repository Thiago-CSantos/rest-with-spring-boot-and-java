package br.com.thiago.dto;

import br.com.thiago.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"id", "address", "firstName", "lastName", "gender"}) // muda a ordem
public record PersonVo(Long id, @JsonProperty("primeiro_nome") String firstName, String lastName, String address,
                       @JsonIgnore String gender) {

    public PersonVo(Person person) {
        this(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender());
    }

}

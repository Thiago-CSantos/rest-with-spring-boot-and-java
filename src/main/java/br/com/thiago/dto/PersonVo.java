package br.com.thiago.dto;

import br.com.thiago.model.Person;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;


public class PersonVo extends RepresentationModel<PersonVo> {
    @Mapping("id")
    private Long id_chave;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVo(Long id_chave, String firstName, String lastName, String address, String gender) {
        this.id_chave = id_chave;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public PersonVo(Person person) {
        this(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender());
    }

    public Long getId_chave() {
        return id_chave;
    }

    public void setId_chave(Long id_chave) {
        this.id_chave = id_chave;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVo personVo)) return false;
        if (!super.equals(o)) return false;

        if (!id_chave.equals(personVo.id_chave)) return false;
        if (!firstName.equals(personVo.firstName)) return false;
        if (!lastName.equals(personVo.lastName)) return false;
        if (!address.equals(personVo.address)) return false;
        return gender.equals(personVo.gender);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id_chave.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }
}

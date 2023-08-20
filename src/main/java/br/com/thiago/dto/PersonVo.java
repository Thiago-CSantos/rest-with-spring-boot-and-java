package br.com.thiago.dto;

import br.com.thiago.model.Person;
import com.github.dozermapper.core.Mapping;
import jakarta.persistence.Column;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;


public class PersonVo extends RepresentationModel<PersonVo> {
    @Mapping("id")
    private Long id_chave;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String enabled;

    public PersonVo() {
    }

    public PersonVo(Long id_chave, String firstName, String lastName, String address, String gender, String enabled) {
        this.id_chave = id_chave;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.enabled = enabled;
    }

    public PersonVo(Person person) {
        this(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender(), person.getEnabled());
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

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVo personVo)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId_chave(), personVo.getId_chave()) && Objects.equals(getFirstName(), personVo.getFirstName()) && Objects.equals(getLastName(), personVo.getLastName()) && Objects.equals(getAddress(), personVo.getAddress()) && Objects.equals(getGender(), personVo.getGender()) && Objects.equals(getEnabled(), personVo.getEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId_chave(), getFirstName(), getLastName(), getAddress(), getGender(), getEnabled());
    }
}

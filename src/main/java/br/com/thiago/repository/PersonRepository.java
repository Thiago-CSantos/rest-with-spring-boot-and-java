package br.com.thiago.repository;

import br.com.thiago.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Modifying
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
    void disablePerson(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Person p SET p.enabled = true WHERE p.id =:id")
    void enabledPerson(@Param("id") Long id);

    //vai buscar apenas por as letras que tem nos nomes
    //exemplo: 'Leandro' passo no paramentro 'and'
    @Query("SELECT p FROM Person p WHERE p.firstName LIKE LOWER(CONCAT('%',:firstName,'%'))")
    Page<Person> findPersonsByNames(@Param("firstName") String firstName, Pageable pageable);
}

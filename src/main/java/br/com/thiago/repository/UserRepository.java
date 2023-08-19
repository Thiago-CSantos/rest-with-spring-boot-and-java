package br.com.thiago.repository;

import br.com.thiago.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
        // Não é uma QUERY SQL - JPQL lidando com o objeto mapeado
    User findByUsername(@Param("userName") String userName);
}

package com.example.developedscheduler.repository;

import com.example.developedscheduler.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Id> {

    List<User> findByNameIsLike(String name);

    @Query("select substring(u.name,0,:length) from User u where u.name = :name")
    List<String> findNameListBySubstring(@Param("name") String name, @Param("length") int length);


    boolean existsUserByName(String name);


    @Query("select count(u.name) from User u where substring(u.name, 1, :length) = :name")
    int countUserByName(@Param("name") String name, @Param("length") int length);

    List<User> name(String name);

    default User findUserByNameOrElseThrow(String username) {

        return findUserByName(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저명입니다."));
    }

    Optional<User> findUserByName(String username);


//    default User findByIdOrElseThrow(Long id) {
//        return findById(id).orElseThrow()
//    }
}

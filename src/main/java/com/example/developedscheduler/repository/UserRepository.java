package com.example.developedscheduler.repository;

import com.example.developedscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("select substring(u.name,0,:length) from User u where u.name = :name")
//    List<String> findNameListBySubstring(@Param("name") String name, @Param("length") int length);

//    @Query("select count(u.name) from User u where substring(u.name, 1, :length) = :name")
//    int countUserByName(@Param("name") String name, @Param("length") int length);

//    List<User> findByNameIsLike(String name);

  // --------위는 나중에 복습용으로 놔두었습니다..--------사용하진 않습니다

    //유저명이 존재하는지 확인. 없을 시 예외처리
    default User findUserByNameOrElseThrow(String username) {

        return findUserByName(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저명입니다."));
    }

    //유저명으로 유저 찾기. Optional 유저 객체 반환
    Optional<User> findUserByName(String username);

    //유저 id가 존재하는지 확인, 없을시 예외처리
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저 아이디입니다."));
    }

    //유저명이 존재하는지 확인하는 boolean 메서드
    boolean existsUserByName(String name);

    List<User> findByEmailAndPassword(String email, String password);

    List<User> findUserByEmailAndPassword(String email, String password);

    Optional<User> findIdByEmailAndPassword(String email, String password);

    default User findIdByEmailAndPasswordOrElseThrow(String email, String password){
        return findIdByEmailAndPassword(email,password).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 이메일 또는 비밀번호입니다. 재입력해주세요."));
    }

    boolean existsUserByEmail(String email);

    Long id(Long id);
}

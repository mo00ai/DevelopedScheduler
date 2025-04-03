package com.example.developedscheduler.domain.repository;

import com.example.developedscheduler.domain.entity.User;
import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

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

        return findUserByName(username).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    //유저명으로 유저 찾기. Optional 유저 객체 반환
    Optional<User> findUserByName(String username);

    //유저 id가 존재하는지 확인, 없을시 예외처리
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }





    //----------회원가입--------------
    //중복 유저가 존재하는지 확인
    boolean existsUserByName(String name);

    //중복 이메일이 존재하는지 확인
    boolean existsUserByEmail(String email);






    //----------로그인--------------
    Optional<User> findIdByEmailAndPassword(String email, String password);

    default User findIdByEmailAndPasswordOrElseThrow(String email, String password){
        return findIdByEmailAndPassword(email,password).orElseThrow(()-> new CustomException(ErrorCode.LOGIN_FAIL));
    }

}

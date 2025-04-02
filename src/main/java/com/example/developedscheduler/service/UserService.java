package com.example.developedscheduler.service;

import com.example.developedscheduler.dto.user.UserResponseDto;
import com.example.developedscheduler.entity.User;
import com.example.developedscheduler.repository.ScheduleRepository;
import com.example.developedscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public UserResponseDto signUpUser(String name, String email, String password) {

        //사용자가 작성한 이름이 db에 있는지 확인 boolean 반환
        boolean existsUserByName = userRepository.existsUserByName(name);

        //있다면 예외 날리기
        if(existsUserByName) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 이름이 이미 존재합니다. 다른 이름을 입력하세요");
        }

        //없다면 새로운 유저 생성
        User user = new User(name, email, password);

        //유저 저장
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(),savedUser.getName(),savedUser.getEmail());
    }


    public List<UserResponseDto> findAllUsers() {

        //전체 유저 조회
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findUserById(Long id) {

        //특정 아이디로 유저 조회
        User foundUser = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(foundUser.getId(), foundUser.getName(), foundUser.getEmail());
    }

    @Transactional
    public UserResponseDto updateUser(Long id, String name, String email, String oldPassword, String newPassword) {

        User foundUser = userRepository.findByIdOrElseThrow(id);

        //사용자가 수정하길 원하는 데이터만 입력되고, 입력하지 않은 데이터는 수정 전 원본의 데이터가 적용됨.
        if(name == null) {
            name = foundUser.getName();
        }

        if(email == null) {
            email = foundUser.getEmail();
        }

        if(oldPassword == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호를 입력해주세요");
        }

        if(foundUser.getPassword().equals(oldPassword)) {
            if(newPassword == null) {
                foundUser.updateUser(name,email,oldPassword);
            } else {
                foundUser.updateUser(name,email,newPassword);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호가 틀렸습니다.");
        }

        return UserResponseDto.toDto(foundUser);
    }


    @Transactional
    public void deleteUser(Long id,String password) {

        //유저 있는지 확인
        User foundUser = userRepository.findByIdOrElseThrow(id);

        if(password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호를 입력해주세요");
        }

        if(!password.equals(foundUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호가 틀렸습니다. 재입력해주세요.");
        }

        //유저가 있다면 그 유저의 아이디로 작성된 모든 일정 삭제
        scheduleRepository.deleteSchedulesByUser_Id(id);

        //유저 삭제
        userRepository.deleteById(id);

    }
}

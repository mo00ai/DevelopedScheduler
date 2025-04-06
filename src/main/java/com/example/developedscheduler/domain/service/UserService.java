package com.example.developedscheduler.domain.service;

import com.example.developedscheduler.domain.config.PasswordEncoder;
import com.example.developedscheduler.domain.dto.user.UserResponseDto;
import com.example.developedscheduler.domain.entity.User;
import com.example.developedscheduler.domain.repository.ScheduleRepository;
import com.example.developedscheduler.domain.repository.UserRepository;
import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signUpUser(String name, String email, String password) {

        //사용자가 작성한 이름이 db에 있는지 확인 boolean 반환
        boolean existsUserByName = userRepository.existsUserByName(name);

        //사용자 이메일이 이미 존재하는지 확인
        boolean existsUserByEmail = userRepository.existsUserByEmail(email);

        //있다면 예외 날리기
        if(existsUserByName) {
            throw new CustomException(ErrorCode.NAME_DUPLICATION, "중복 이름이 이미 존재합니다. 다른 이름을 입력하세요");
        }

        if(existsUserByEmail) {
            throw new CustomException(ErrorCode.EMAIL_DUPLICATION,"중복 이메일이 이미 존재합니다. 다른 이메일을 입력하세요.");
        }

        //없다면 새로운 유저 생성
        User user = new User(name, email, passwordEncoder.encode(password));

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

        if(passwordEncoder.matches(oldPassword, foundUser.getPassword())) {
            if(newPassword != null) {
                foundUser.updateUser(name,email,passwordEncoder.encode(newPassword));
            }
        } else {
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        }

        return UserResponseDto.toDto(foundUser);
    }


    @Transactional
    public void deleteUser(Long id,String password) {

        //유저 있는지 확인
        User foundUser = userRepository.findByIdOrElseThrow(id);

        if(passwordEncoder.matches(password, foundUser.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        }

//        //유저가 있다면 그 유저의 아이디로 작성된 모든 일정 삭제
//        scheduleRepository.deleteSchedulesByUser_Id(id);

        //유저 삭제
        userRepository.deleteById(id);

    }
}

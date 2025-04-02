package com.example.developedscheduler.service;

import com.example.developedscheduler.dto.user.UserResponseDto;
import com.example.developedscheduler.entity.User;
import com.example.developedscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto signUpUser(String name, String address) {

//        List<String> sameNameList = userRepository.findNameListBySubstring(name, name.length());

        //1. 유니크 기능 해야함
        //2. 테이블에 가서 작성한 이름이 있는지 확인
        //-그럼 카운트를 해야겠다...
        //있으면

//        boolean isNameExist = userRepository.existsUserByName(name);


//
//        if(isNameExist) {
//
//        }

        int countSameName = userRepository.countUserByName(name, name.length());

        if(countSameName>0) {
            name += String.valueOf(countSameName+1);
        }

        User user = new User(name, address);

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(),savedUser.getName(),savedUser.getAddress());
    }


    public List<UserResponseDto> findAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }
}

package com.example.developedscheduler.domain.service;

import com.example.developedscheduler.domain.config.PasswordEncoder;
import com.example.developedscheduler.domain.dto.user.LoginResponseDto;
import com.example.developedscheduler.domain.entity.User;
import com.example.developedscheduler.domain.repository.UserRepository;
import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(String email, String password) {

        boolean existsUserByEmail = userRepository.existsUserByEmail(email);

        if(!existsUserByEmail) {
            throw new CustomException(ErrorCode.LOGIN_FAIL,"존재하지 않는 이메일입니다.");
        }

        User foundUser = userRepository.findUserByEmail(email);

        if(!passwordEncoder.matches(password, foundUser.getPassword())) {
            throw new CustomException(ErrorCode.LOGIN_FAIL,"비밀번호가 틀렸습니다.");
        }

        return new LoginResponseDto(foundUser.getId(),foundUser.getName(),foundUser.getEmail());
    }
}

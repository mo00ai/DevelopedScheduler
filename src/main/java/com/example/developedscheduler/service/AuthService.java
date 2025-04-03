package com.example.developedscheduler.service;

import com.example.developedscheduler.dto.user.LoginResponseDto;
import com.example.developedscheduler.entity.User;
import com.example.developedscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public LoginResponseDto login(String email, String password) {

        User loginUser = userRepository.findIdByEmailAndPasswordOrElseThrow(email, password);

        return new LoginResponseDto(loginUser.getId(),loginUser.getName(),loginUser.getPassword());
    }
}

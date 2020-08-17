package com.budget.manager.service;

import com.budget.manager.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.stream.Stream;

public interface AuthService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByEmail(String email);

    UserDto getUserByUserId(String userId);

    void verifyEmailToken(String token);

    String getRequestPasswordToken(UserDto userDto);

    void saveRequestPasswordToken(String token, UserDto userDto);

    void resetPassword(String token, String password);
}

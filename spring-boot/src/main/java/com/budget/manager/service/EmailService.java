package com.budget.manager.service;

import com.budget.manager.shared.dto.UserDto;

public interface EmailService {

    void sendEmailVerificationEmail(UserDto userDto, String appBaseUrl);

    void sendPasswordResetEmail(UserDto userDto, String token);
}

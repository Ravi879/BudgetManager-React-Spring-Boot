package com.budget.manager.service.impl;

import com.budget.manager.exception.InvalidTokenException;
import com.budget.manager.exception.ResourceAlreadyExistsException;
import com.budget.manager.exception.ResourceNotFoundException;
import com.budget.manager.repository.PasswordResetTokenRepository;
import com.budget.manager.repository.UserRepository;
import com.budget.manager.repository.entity.PasswordResetTokenEntity;
import com.budget.manager.repository.entity.UserEntity;
import com.budget.manager.service.AuthService;
import com.budget.manager.shared.dto.UserDto;
import com.budget.manager.shared.utils.JwtUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;
    private final PasswordResetTokenRepository passwordTokenRepository;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtils jwtUtils, PasswordResetTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
        mapper = new ModelMapper();
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        //check id email address already register
        if (userRepository.findByEmail(userDto.getEmail()).isPresent())
            throw new ResourceAlreadyExistsException("Email address is already registered.");

        // populate dto properties before inserting to database
        userDto.setUserId(RandomStringUtils.random(10, true, true));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        // set email verification token
        userDto.setEmailVerificationToken(jwtUtils.generateToken(userDto.getEmail()));
        //store details to database
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        UserEntity storedUserEntity = userRepository.save(userEntity);
        return mapper.map(storedUserEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntity -> mapper.map(userEntity, UserDto.class))
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Email address is not registered.");
                });
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .map(userEntity -> mapper.map(userEntity, UserDto.class))
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Invalid user id.");
                });
    }

    @Override
    public void verifyEmailToken(String token) {
        // Find user by token
        UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token)
                .orElseThrow(() -> {
                    throw new InvalidTokenException("Invalid verification link.");
                });
        // check for token expiration
        if (jwtUtils.isTokenExpired(token))
            throw new InvalidTokenException("Verification link has been expired");

        userEntity.setEmailVerificationToken(null);
        userEntity.setEmailVerificationStatus(Boolean.TRUE);
        userRepository.save(userEntity);
    }

    @Override
    public String getRequestPasswordToken(UserDto userDto) {
        // generate password reset token
        return jwtUtils.generateToken(userDto.getUserId() + userDto.getEmail());
    }

    // save password reset token to 'PasswordResetToken' database
    @Override
    public void saveRequestPasswordToken(String token, UserDto userDto) {
        // map to UserEntity
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        // save token to repository
        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);
        passwordTokenRepository.save(passwordResetTokenEntity);
    }

    // update password
    @Override
    public void resetPassword(String token, String password) {
        if (jwtUtils.isTokenExpired(token)) {
            throw new InvalidTokenException("Your reset password link has been expired. Please issue new reset password request.");
        }

        PasswordResetTokenEntity passwordResetTokenEntity = passwordTokenRepository.findByToken(token)
                .orElseThrow(() -> {
                    throw new InvalidTokenException("Invalid link.");
                });

        // encode new password
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        // Update User password in database
        UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
        userEntity.setEncryptedPassword(encodedPassword);
        userRepository.save(userEntity);

        // Remove Password Reset token from database
        passwordTokenRepository.delete(passwordResetTokenEntity);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = getUserByEmail(email);
        return new User(userDto.getEmail(), userDto.getEncryptedPassword(), new ArrayList<>());
    }

}

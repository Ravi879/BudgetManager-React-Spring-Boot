package com.budget.manager.ui.controller;

import com.budget.manager.exception.EmailNotVerifiedException;
import com.budget.manager.service.AuthService;
import com.budget.manager.service.EmailService;
import com.budget.manager.shared.dto.UserDto;
import com.budget.manager.shared.utils.JwtUtils;
import com.budget.manager.ui.modal.request.RequestPasswordResetModel;
import com.budget.manager.ui.modal.request.ResetPasswordModel;
import com.budget.manager.ui.modal.request.UserLogin;
import com.budget.manager.ui.modal.request.UserRequest;
import com.budget.manager.ui.modal.response.UserLoginResponse;
import com.budget.manager.ui.modal.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class AuthController {

    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;
    private final EmailService emailService;

    @Value("${client.url}")
    String clientUrl;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil, EmailService emailService) {
        modelMapper = new ModelMapper();
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.emailService = emailService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid UserLogin userLogin) throws BadCredentialsException {
        // check if email address verify or not
        UserDto userDto = authService.getUserByEmail(userLogin.getEmail());
        if (!userDto.getEmailVerificationStatus()) {
            String errorMsg = MessageFormat.format("email={0} userId={1}", userDto.getEmail(), userDto.getUserId());
            throw new EmailNotVerifiedException("email.not.verified", errorMsg);
        }
        // authenticate user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLogin.getEmail(),
                userLogin.getPassword(),
                new ArrayList<>()
        ));

        log.info("UserLogin -- login.user -- userId={}", userDto.getUserId());

        //send jwt token back to user
        String jwtToken = jwtTokenUtil.generateToken(userDto.getUserId());
        return ResponseEntity.ok(new UserLoginResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        // map UserRequest to UserDto -> create user -> map to UserResponse -> send it to client
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto storedUserDetails = authService.createUser(userDto);
        //send verification email
        emailService.sendEmailVerificationEmail(userDto, getAppBaseUrl());

        log.info("UserRegister -- create.user -- userId={}", storedUserDetails.getUserId());

        //send stored user details as response
        UserResponse returnValue = modelMapper.map(storedUserDetails, UserResponse.class);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @GetMapping(path = "/email-verification")
    public ModelAndView verifyEmailToken(@RequestParam @NotBlank(message = "token.not.empty") String token) {
        // verify token
        authService.verifyEmailToken(token);
        // redirecting to client application
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + clientUrl);
        modelAndView.addObject("emailVerification", true);
        return modelAndView;
    }

    @PostMapping(path = "/request-password-reset")
    public ResponseEntity<Void> requestPasswordReset(@RequestBody @Valid RequestPasswordResetModel passwordResetRequestModel) {
        // get user details
        UserDto userDto = authService.getUserByEmail(passwordResetRequestModel.getEmail());
        // get token
        String token = authService.getRequestPasswordToken(userDto);
        // save token
        authService.saveRequestPasswordToken(token, userDto);
        // send email
        emailService.sendPasswordResetEmail(userDto, token);

        log.info("RequestPasswordReset -- request.pwd.rst -- userId={}", userDto.getUserId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/password-reset")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordModel passwordResetModel) {

        authService.resetPassword(passwordResetModel.getToken(),
                passwordResetModel.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // returns the base url of this spring boot application, ex, for local running returns http://localhost:8080
    private String getAppBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

}

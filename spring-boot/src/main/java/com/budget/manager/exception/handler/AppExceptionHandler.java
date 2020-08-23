package com.budget.manager.exception.handler;

import com.budget.manager.exception.EmailNotVerifiedException;
import com.budget.manager.exception.InvalidTokenException;
import com.budget.manager.exception.ResourceAlreadyExistsException;
import com.budget.manager.exception.ResourceNotFoundException;
import com.budget.manager.exception.model.ErrorListMessages;
import com.budget.manager.exception.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.budget.manager.shared.type.Errors.*;
import static com.budget.manager.shared.utils.MsgSrcUtils.getMessage;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public AppExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, HttpServletRequest request) {
        log.error("Exception -- server.error -- exception={}", ExceptionUtils.getRootCauseMessage(ex));

        ErrorMessage errorMessage = new ErrorMessage(
                getMessage(messageSource, "server.error"), ExceptionUtils.getRootCauseMessage(ex),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI());
        ex.printStackTrace();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        log.error("BadCredentialsException -- bad.credentials -- {}", ExceptionUtils.getRootCauseMessage(ex));

        ErrorMessage errorMessage = new ErrorMessage(
                getMessage(messageSource, "bad.credentials"), BAD_CREDENTIALS,
                HttpStatus.UNAUTHORIZED.value(), request.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        log.error("ResourceNotFoundException -- {} -- {}", ex.getMessageCode(), ex.getErrorInfo());

        ErrorMessage errorMessage = new ErrorMessage(
                getMessage(messageSource, ex.getMessageCode()), RESOURCE_NOT_FOUND,
                HttpStatus.NOT_FOUND.value(), request.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, HttpServletRequest request) {
        log.error("ResourceAlreadyExistsException -- {} -- {}", ex.getMessageCode(), ex.getErrorInfo());

        ErrorMessage errorMessage = new ErrorMessage(
                getMessage(messageSource, ex.getMessageCode()), RESOURCE_ALREADY_EXISTS,
                HttpStatus.CONFLICT.value(), request.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = EmailNotVerifiedException.class)
    public ResponseEntity<ErrorMessage> handleEmailNotVerifiedException(EmailNotVerifiedException ex, HttpServletRequest request) {
        log.error("EmailNotVerifiedException -- {} -- {}", ex.getMessageCode(), ex.getErrorInfo());

        ErrorMessage errorMessage = new ErrorMessage(
                getMessage(messageSource, ex.getMessageCode()), EMAIL_NOT_VERIFIED,
                HttpStatus.CONFLICT.value(), request.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorMessage> handleInvalidTokenException(InvalidTokenException ex, HttpServletRequest request) {
        log.error("InvalidTokenException -- {} -- {}", ex.getMessageCode(), ex.getErrorInfo());

        ErrorMessage errorMessage = new ErrorMessage(
                getMessage(messageSource, ex.getMessageCode()), INVALID_TOKEN,
                HttpStatus.CONFLICT.value(), request.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> messageCodes = new ArrayList<>();
        BindingResult bindingResult = ex.getBindingResult();

        List<String> messages = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    String messageCode = fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : fieldError.getField();
                    messageCodes.add(messageCode);
                    return getMessage(messageSource, messageCode);
                })
                .sorted()
                .collect(Collectors.toList());

        log.error("MethodArgumentNotValidException -- validation.error -- {}", String.join(" ", messageCodes));

        ErrorListMessages errorMessage = new ErrorListMessages(
                messages, VALIDATION_ERROR,
                HttpStatus.BAD_REQUEST.value(), request.getDescription(false).substring(4));

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorListMessages> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<String> messageCodes = new ArrayList<>();

        List<String> messages = ex.getConstraintViolations()
                .stream()
                .map(constraint -> {
                    String messageCode = constraint.getMessage();
                    messageCodes.add(messageCode);
                    return getMessage(messageSource, messageCode);
                })
                .sorted()
                .collect(Collectors.toList());

        log.error("ConstraintViolationException -- constraint.error -- {}", String.join(" ", messageCodes));

        ErrorListMessages errorMessage = new ErrorListMessages(
                messages, VALIDATION_ERROR,
                HttpStatus.BAD_REQUEST.value(), request.getRequestURI());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


}

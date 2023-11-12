package io.github.jubadeveloper.actors.controller.advice;

import io.github.jubadeveloper.several.exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnauthenticatedExceptionAdviceController {
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String interceptAuthenticationException (AuthenticationException authenticationException) {
        return authenticationException.getMessage();
    }
}

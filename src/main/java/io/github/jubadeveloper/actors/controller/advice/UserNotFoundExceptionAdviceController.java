package io.github.jubadeveloper.actors.controller.advice;

import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundExceptionAdviceController {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()
    public String interceptUserNotFoundException (UserNotFoundException userException) {
        return userException.getMessage();
    }
}

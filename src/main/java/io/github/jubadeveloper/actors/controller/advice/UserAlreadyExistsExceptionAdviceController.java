package io.github.jubadeveloper.actors.controller.advice;

import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class UserAlreadyExistsExceptionAdviceController {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody()
    public String interceptUserAlreadyExistsException (UserAlreadyExistsException userException) {
        return userException.getMessage();
    }
}

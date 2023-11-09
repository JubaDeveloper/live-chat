package org.jubadeveloper.actors.controller.advice;

import org.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAlreadyExistsUsernameExceptionAdviceController {
    @ExceptionHandler(UserAlreadyExistsUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody()
    public String interceptUserAlreadyExistsException (UserAlreadyExistsUsernameException userException) {
        return userException.getMessage();
    }
}

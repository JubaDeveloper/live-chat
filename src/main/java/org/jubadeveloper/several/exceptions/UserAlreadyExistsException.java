package org.jubadeveloper.several.exceptions;

public class UserAlreadyExistsException extends Exception {
    String exceptionPattern = "User already exists with given email: %s";
    public UserAlreadyExistsException (String email) {
        super(String.format("User already exists with given email", email));
    }
}

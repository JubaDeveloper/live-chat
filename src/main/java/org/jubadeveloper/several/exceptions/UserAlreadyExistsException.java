package org.jubadeveloper.several.exceptions;

public class UserAlreadyExistsException extends Exception {
    public static final String exceptionPattern = "User already exists with given email: %s";
    public UserAlreadyExistsException (String email) {
        super(String.format(exceptionPattern, email));
    }
}

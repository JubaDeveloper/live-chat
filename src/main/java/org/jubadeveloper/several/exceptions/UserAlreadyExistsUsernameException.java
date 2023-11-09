package org.jubadeveloper.several.exceptions;

public class UserAlreadyExistsUsernameException extends Exception {
    public static final String exceptionPattern = "User already exists with given username: %s";
    public UserAlreadyExistsUsernameException (String username) {
        super(String.format(exceptionPattern, username));
    }
}

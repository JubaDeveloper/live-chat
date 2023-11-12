package io.github.jubadeveloper.several.exceptions;

public class UserNotFoundException extends Exception {
    public static final String exceptionPattern = "User with email: %s was not found";
    public UserNotFoundException (String email) {
        super(String.format(exceptionPattern, email));
    }
}

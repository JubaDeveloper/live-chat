package io.github.jubadeveloper.several.exceptions;

public class InvalidLoginException extends Exception {
    public static final String exceptionPattern = "Invalid login";
    public InvalidLoginException () {
        super(exceptionPattern);
    }
}

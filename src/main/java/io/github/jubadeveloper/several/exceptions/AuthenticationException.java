package io.github.jubadeveloper.several.exceptions;

public class AuthenticationException extends Exception {
    public static final String exceptionPatternV1 = "You aren't authenticated to complete this operation";
    public AuthenticationException () {
        super(exceptionPatternV1);
    }
}

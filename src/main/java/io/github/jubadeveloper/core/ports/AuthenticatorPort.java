package io.github.jubadeveloper.core.ports;


import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.several.exceptions.AuthenticationException;

public interface AuthenticatorPort {
    String auth (User user);
    String checkAuth (String token) throws AuthenticationException;
    String updateToken (String token) throws AuthenticationException;
    String getAuthRequest (String authHeader);
}

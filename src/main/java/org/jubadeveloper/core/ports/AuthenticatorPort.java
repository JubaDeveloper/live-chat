package org.jubadeveloper.core.ports;


import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.AuthenticationException;

public interface AuthenticatorPort {
    String auth (User user);
    String checkAuth (String token) throws AuthenticationException;
    String updateToken (String token) throws AuthenticationException;
    String getAuthRequest (String authHeader);
}

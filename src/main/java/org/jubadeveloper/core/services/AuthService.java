package org.jubadeveloper.core.services;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.ports.AuthenticatorPort;
import org.jubadeveloper.core.services.contracts.AuthServiceContract;
import org.jubadeveloper.several.exceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceContract {
    @Autowired
    AuthenticatorPort authenticatorPort;
    @Override
    public String auth(User user) {
        return authenticatorPort.auth(user);
    }

    @Override
    public String checkAuth(String token) throws AuthenticationException {
        return authenticatorPort.checkAuth(token);
    }

    @Override
    public String updateToken(String token) throws AuthenticationException {
        return authenticatorPort.updateToken(token);
    }

    @Override
    public String getAuthToken(String authToken) {
        return authenticatorPort.getAuthRequest(authToken);
    }
}

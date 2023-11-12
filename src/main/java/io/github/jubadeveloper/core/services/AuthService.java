package io.github.jubadeveloper.core.services;

import io.github.jubadeveloper.core.ports.AuthenticatorPort;
import io.github.jubadeveloper.core.services.contracts.AuthServiceContract;
import io.github.jubadeveloper.several.exceptions.AuthenticationException;
import io.github.jubadeveloper.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.List;

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

    public static String getTokenFromCookies(Enumeration<String> cookies) {
        String authToken = null;
        String firstElementCookies = null;
        while (cookies.hasMoreElements()) {
            String cookie = cookies.nextElement();
            if (firstElementCookies == null) {
                firstElementCookies = cookie;
            }
            String[] splitCookies = cookie.split("=");
            if (splitCookies[0].equals("X-Authorization")) {
                String[] values = splitCookies[1].split(";");
                if (values.length == 0) {
                    authToken = null;
                } else {
                    authToken = values[0];
                }
            }
        }
        if (authToken == null) {
            if (firstElementCookies == null) return null;
            String[] splitCookies = firstElementCookies.split(";");
            for (String cookie: splitCookies) {
                String[] splitCookie = cookie.split("=");
                if (splitCookie[0].contains("X-Authorization")) {
                    if (splitCookie.length < 2) {
                        break;
                    }
                    authToken = splitCookie[1];
                    break;
                }
            }
        }
        return authToken;
    }

    public static String getTokenFromCookies (List<String> cookies) {
        String authToken = null;
        for (String cookie: cookies) {
            String[] splitCookies = cookie.split("=");
            if (splitCookies[0].equals("X-Authorization")) {
                String[] values = splitCookies[1].split(";");
                if (values.length != 0) {
                    authToken = values[0];
                    break;
                }
            }
        }
        if (authToken == null) {
            String firstCookies = cookies.get(0);
            String[] splitCookies = firstCookies.split(";");
            for (String cookie: splitCookies) {
                String[] splitCookie = cookie.split("=");
                if (splitCookie[0].contains("X-Authorization")) {
                    authToken = splitCookie[1];
                    break;
                }
            }
        }
        return authToken;
    }
}

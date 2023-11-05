package org.jubadeveloper.core.services.websocket.auth;

import org.jubadeveloper.several.interfaces.AuthValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsocketAuthService implements AuthValidator {
    @Override
    public boolean validateAuthToken(String token) {
        return token != null; // Test validator not fully implemented yet
    }
    public String getAuthTokenFromCookies (List<String> cookies) {
        String authToken = null;
        for (String cookie: cookies) {
            String[] splitCookies = cookie.split("=");
            if (splitCookies[0].equals("X-Authorization")) {
                String[] values = splitCookies[1].split(";");
                authToken = values[0];
            }
        }
        return authToken;
    }
}

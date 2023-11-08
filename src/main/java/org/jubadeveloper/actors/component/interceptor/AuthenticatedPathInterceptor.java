package org.jubadeveloper.actors.component.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jubadeveloper.core.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.List;

@Component
public class AuthenticatedPathInterceptor implements HandlerInterceptor {
    @Autowired AuthService authService;
    public AuthenticatedPathInterceptor (@Autowired AuthService authService) {
        this.authService = authService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> cookies = request.getHeaders("cookie");
        String authToken = AuthService.getTokenFromCookies(cookies);
        if (authToken != null && !authToken.equals("")) {
            String token = authService.getAuthToken(authToken);
            // Refresh token and pass the newer to controller
            String userId = authService.checkAuth(token);
            request.setAttribute("email", userId);
            if (userId != null) {
                String newToken = authService.updateToken(token);
                response.setHeader("Set-Cookie", "X-Authorization=" +  String.format("%s", newToken) + ";path=/");
                return true;
            }
        }
        response.sendError(401, "Invalid authorization token");
        return false;
    }
}
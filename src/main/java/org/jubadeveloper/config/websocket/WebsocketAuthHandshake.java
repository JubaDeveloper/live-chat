package org.jubadeveloper.config.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jubadeveloper.core.services.websocket.auth.WebsocketAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Component
public class WebsocketAuthHandshake implements HandshakeInterceptor {
    Logger logger = LogManager.getLogger(WebsocketAuthHandshake.class);
    @Autowired
    WebsocketAuthService websocketAuthService;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // We should verify token and validate it
        List<String> cookies = request.getHeaders().get("cookie");
        if (cookies != null) {
            return websocketAuthService.validateAuthToken(websocketAuthService.getAuthTokenFromCookies(cookies));
        }
        return websocketAuthService.validateAuthToken(null);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {}
}

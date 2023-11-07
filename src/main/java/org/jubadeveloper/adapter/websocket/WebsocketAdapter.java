package org.jubadeveloper.adapter.websocket;

import org.jubadeveloper.core.ports.WebsocketPort;
import org.jubadeveloper.core.services.AuthService;
import org.jubadeveloper.several.exceptions.AuthenticationException;
import org.jubadeveloper.several.interfaces.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebsocketAdapter extends TextWebSocketHandler implements WebsocketPort {
    MessageListener messageListener = null;
    @Autowired
    AuthService authService;
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws AuthenticationException {
        List<String> cookies = session.getHandshakeHeaders().get("cookie");
        if (cookies == null) cookies = new ArrayList<>();
        String auth = AuthService.getTokenFromCookies(cookies);
        authService.checkAuth(auth);
        this.messageListener.onMessage(message.getPayload());
    }

    @Override
    public void addMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}


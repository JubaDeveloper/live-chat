package org.jubadeveloper.adapter.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jubadeveloper.adapter.websocket.session.Session;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.ports.WebsocketPort;
import org.jubadeveloper.core.services.AuthService;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.AuthenticationException;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.jubadeveloper.several.interfaces.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class WebsocketAdapter extends TextWebSocketHandler implements WebsocketPort {
    MessageListener messageListener = null;
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;
    private final List<Session> connections = new ArrayList<>();
    private final Logger logger = LogManager.getLogger(WebsocketAdapter.class);
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws AuthenticationException, UserNotFoundException, ChannelNotFoundException, IOException {
        // Channel validation and user is did here
        String path = Objects.requireNonNull(session.getUri()).getPath();
        String[] splitPath = path.split("/");
        if (splitPath[1] != null) {
            Long channelId = Long.valueOf(splitPath[2]);
            List<String> cookies = session.getHandshakeHeaders().get("cookie");
            if (cookies == null) cookies = new ArrayList<>();
            String auth = AuthService.getTokenFromCookies(cookies);
            String email = authService.checkAuth(auth);
            User user = userService.getUser(email);
            List<User> users = channelService.getChannelById(channelId)
                    .getUsers().stream().filter(user1 -> user1.getEmail().equals(user.getEmail()))
                    .toList();
//            if (users.size() > 0) {
//                this.messageListener.onMessage(user, message.getPayload());
//            }
            this.messageListener.onMessage(user, message.getPayload());
            TextMessage textMessage = new TextMessage(user.getUsername() + ":" + LocalDate.now() + ":" + message.getPayload());
            session.sendMessage(textMessage);
            logger.info("Clients connected count: " + connections.size());
            for (Session session1: connections) {
                String channelIdMessage = session.getUri().getPath().split("channel/")[1];
                if (!session1.getChannelId().equals(channelIdMessage)
                        || session1.getWebSocketSession().getId().equals(session.getId())) continue;
                session1.getWebSocketSession().sendMessage(textMessage);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        if (session.getUri() == null) throw new Exception("No uri");
        String[] splitPath = session.getUri().getPath().split("channel/");
        Session createdSession = new Session(splitPath[1], session);
        connections.add(createdSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        connections.removeIf(session1 -> session.getId().equals(session1.getWebSocketSession().getId()));
    }

    @Override
    public void addMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}


package io.github.jubadeveloper.adapter.websocket;

import io.github.jubadeveloper.adapter.websocket.session.Session;
import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.services.AuthService;
import io.github.jubadeveloper.several.exceptions.AuthenticationException;
import jakarta.annotation.Nonnull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.jubadeveloper.core.ports.WebsocketPort;
import io.github.jubadeveloper.core.services.ChannelService;
import io.github.jubadeveloper.core.services.UserService;
import io.github.jubadeveloper.several.exceptions.ChannelNotFoundException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
import io.github.jubadeveloper.several.interfaces.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    protected void handleTextMessage(WebSocketSession session, @Nonnull TextMessage message) throws AuthenticationException, UserNotFoundException, ChannelNotFoundException, IOException {
        // Channel validation and user is did here
        String path = Objects.requireNonNull(session.getUri()).getPath();
        LocalTime messageTime = LocalTime.now();
        LocalDate messageDate = LocalDate.now();
        String[] splitPath = path.split("/");
        if (splitPath[1] != null) {
            Long channelId = Long.valueOf(splitPath[2]);
            List<String> cookies = session.getHandshakeHeaders().get("cookie");
            if (cookies == null) cookies = new ArrayList<>();
            String auth = AuthService.getTokenFromCookies(cookies);
            String email = authService.checkAuth(auth);
            User user = userService.getUser(email);
            this.messageListener.onMessage(user, message.getPayload());
            String finalMessage = user.getUsername() + ":" + messageDate + " - " +  messageTime.toString().split("\\.")[0] + ":" + message.getPayload();
            TextMessage textMessage = new TextMessage(finalMessage);
            saveMessage(channelId, finalMessage);
            session.sendMessage(textMessage);
            propagateMessageToClients(session, textMessage);
        }
    }

    @Override
    public void afterConnectionEstablished(@Nonnull WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        List<String> cookies = session.getHandshakeHeaders().get("cookie");
        if (cookies == null) cookies = new ArrayList<>();
        String auth = AuthService.getTokenFromCookies(cookies);
        String email = authService.checkAuth(auth);
        User user = userService.getUser(email);
        if (session.getUri() == null) throw new Exception("No uri");
        String[] splitPath = session.getUri().getPath().split("channel/");
        Session createdSession = new Session(splitPath[1], session, user);
        connections.add(createdSession);
        getLast10MessagesByChannelIdAndSend(Long.valueOf(splitPath[1]), session);
        // Send authenticated users username in channel
        List<String> authenticatedUsersUsername = new ArrayList<>();
        for (Session session1: connections) {
            authenticatedUsersUsername.add(String.format("\"%s\"", session1.getUser().getUsername()));
        }
        // Send reload signal to each session using user email as identification
        for (Session session1: connections) {
            session1.getWebSocketSession().sendMessage(new TextMessage(session1.getUser().getEmail() + "-" + "users" + "-" + authenticatedUsersUsername));
        }
    }

    @Override
    public void afterConnectionClosed(@Nonnull WebSocketSession session, @Nonnull CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        connections.removeIf(session1 -> session.getId().equals(session1.getWebSocketSession().getId()));
        List<String> authenticatedUsersUsername = new ArrayList<>();
        for (Session session1: connections) {
            authenticatedUsersUsername.add(String.format("\"%s\"", session1.getUser().getUsername()));
        }
        for (Session session1: connections) {
            session1.getWebSocketSession().sendMessage(new TextMessage(session1.getUser().getEmail() + "-" + "users" + "-" + authenticatedUsersUsername));
        }
    }

    @Override
    public void addMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void getLast10MessagesByChannelIdAndSend (Long channelId,
                                                             WebSocketSession webSocketSession) throws ChannelNotFoundException {
        List<String> messages = channelService.getLast10Messages(channelId);
        messages
                .forEach(m -> {
                    try {
                        webSocketSession.sendMessage(new TextMessage(m));
                    } catch (Exception ignored) {}
                });
    }
    public void saveMessage (Long id,
                             String message) throws ChannelNotFoundException {
        channelService.saveMessage(id, message);
    }
    public void propagateMessageToClients (WebSocketSession session, TextMessage message) throws IOException {
        logger.info("Clients connected count: " + connections.size());
        for (Session session1: connections) {
            String channelIdMessage = Objects.requireNonNull(session.getUri()).getPath().split("channel/")[1];
            if (!session1.getChannelId().equals(channelIdMessage)
                    || session1.getWebSocketSession().getId().equals(session.getId())) continue;
            session1.getWebSocketSession().sendMessage(message);
        }
    }
}


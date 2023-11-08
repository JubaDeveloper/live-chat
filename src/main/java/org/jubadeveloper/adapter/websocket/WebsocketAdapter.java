package org.jubadeveloper.adapter.websocket;

import org.jubadeveloper.core.domain.Channel;
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
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws AuthenticationException, UserNotFoundException, ChannelNotFoundException {
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
            if (users.size() > 0) {
                this.messageListener.onMessage(user, message.getPayload());
            }
        }
    }

    @Override
    public void addMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}


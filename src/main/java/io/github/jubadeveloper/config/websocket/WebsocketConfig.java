package io.github.jubadeveloper.config.websocket;

import io.github.jubadeveloper.adapter.websocket.WebsocketAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
    @Autowired
    WebsocketAdapter websocketAdapter;
    @Autowired WebsocketAuthHandshake websocketAuthHandshake;
    @Bean
    WebsocketAdapter websocketHandler () {
        return new WebsocketAdapter();
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(websocketAdapter, "/channel/{id}")
                .addInterceptors(websocketAuthHandshake)
                .setAllowedOriginPatterns("*");
    }
}

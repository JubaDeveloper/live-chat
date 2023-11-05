package org.jubadeveloper.config.websocket;

import org.jubadeveloper.adapter.websocket.WebsocketAdapter;
import org.jubadeveloper.core.services.websocket.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
    @Autowired WebsocketAdapter websocketAdapter;
    @Autowired WebsocketAuthHandshake websocketAuthHandshake;
    @Bean
    WebsocketAdapter websocketHandler () {
        return new WebsocketAdapter();
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(websocketAdapter, "/*")
                .addInterceptors(websocketAuthHandshake)
                .setAllowedOriginPatterns("*");
    }
}

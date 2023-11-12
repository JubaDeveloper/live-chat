package io.github.jubadeveloper.core.services.websocket;

import io.github.jubadeveloper.adapter.websocket.WebsocketAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class WebsocketService {
    Logger logger = LogManager.getLogger(WebsocketService.class);
    @Autowired
    WebsocketAdapter websocketAdapter;
    @Bean
    public void defineListener () {
        websocketAdapter.addMessageListener((user, message) -> {
            logger.info("New client message: User email:" + user.getEmail() + " Message: " + message);
        });
    }

}

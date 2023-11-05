package org.jubadeveloper.core.services.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jubadeveloper.adapter.websocket.WebsocketAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class WebsocketService {
    Logger logger = LogManager.getLogger(WebsocketService.class);
    @Autowired WebsocketAdapter websocketAdapter;
    @Bean
    public void defineListener () {
        websocketAdapter.addMessageListener(message -> {
            logger.info("New client message: " + message);
        });
    }

}

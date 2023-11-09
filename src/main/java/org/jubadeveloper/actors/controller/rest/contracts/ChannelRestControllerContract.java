package org.jubadeveloper.actors.controller.rest.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/panel/api")
public interface ChannelRestControllerContract {
    @PostMapping(value = "/channel", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    Channel apiCreateChannel (@RequestAttribute(value = "email") String userEmail, Channel channel) throws UserNotFoundException; // User should be passed through some auth method
    @PostMapping(value = "/channel/update/{id}", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    Channel apiUpdateChannel (@RequestAttribute(value = "email")String userEmail, @PathVariable(value = "id", name = "id") Long channelId, Channel channel) throws ChannelNotFoundException;
    @GetMapping("/channel/{id}")
    Channel apiGetChannel (@RequestAttribute(value = "email") String userEmail, @PathVariable Long channelId) throws ChannelNotFoundException;
    @GetMapping("/channels")
    List<Channel> apiGetChannels ();
}

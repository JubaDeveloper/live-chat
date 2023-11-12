package io.github.jubadeveloper.actors.controller.rest.contracts;

import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.several.exceptions.ChannelNotFoundException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

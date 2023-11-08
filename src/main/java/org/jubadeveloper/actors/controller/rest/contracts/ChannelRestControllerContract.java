package org.jubadeveloper.actors.controller.rest.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/panel/api")
public interface ChannelRestControllerContract {
    @PostMapping("/channel")
    Channel apiCreateChannel (@RequestAttribute(value = "email") String userEmail, @RequestBody Channel channel) throws UserNotFoundException; // User should be passed through some auth method
    @PutMapping("/channel/{id}")
    Channel apiUpdateChannel (@RequestAttribute(value = "email")String userEmail, @PathVariable Long channelId, @RequestBody Channel channel) throws ChannelNotFoundException;
    @GetMapping("/channel/{id}")
    Channel apiGetChannel (@RequestAttribute(value = "email") String userEmail, @PathVariable Long channelId) throws ChannelNotFoundException;
}

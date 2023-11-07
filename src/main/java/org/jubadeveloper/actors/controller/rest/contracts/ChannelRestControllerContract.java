package org.jubadeveloper.actors.controller.rest.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.springframework.web.bind.annotation.*;

public interface ChannelRestControllerContract {
    @PostMapping("/panel/api/channel")
    Channel apiCreateChannel (@RequestAttribute(value = "email") String userEmail, @RequestBody Channel channel); // User should be passed through some auth method
    @PutMapping("/panel/api/channel/{id}")
    Channel apiUpdateChannel (@RequestAttribute(value = "email")String userEmail, @PathVariable Long channelId, @RequestBody Channel channel) throws ChannelNotFoundException;
    @GetMapping("/panel/api/channel/{id}")
    Channel apiGetChannel (@RequestAttribute(value = "email") String userEmail, @PathVariable Long channelId) throws ChannelNotFoundException;
}

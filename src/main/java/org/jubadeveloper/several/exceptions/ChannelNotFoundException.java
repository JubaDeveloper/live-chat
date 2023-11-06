package org.jubadeveloper.several.exceptions;

public class ChannelNotFoundException extends Exception {
    public static final String exceptionPattern = "Channel: %d was not found";

    public ChannelNotFoundException (Long channelId) {
        super(String.format(exceptionPattern, channelId));
    }
}

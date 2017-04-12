package com.github.ysl3000.twitchapi.api;

/**
 * Created by ysl3000
 */
public interface Channel {
    boolean isMature();
    boolean isPartner();
    String getStatus();
    String getBroadcasterLanguage();
    String getDisplayname();
    String getGameName();
    String getLanguage();
    long getID();
    String getStreamersName();
    String getCreatedAt();
    String getUpdatedAt();
}

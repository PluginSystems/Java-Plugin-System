package com.github.ysl3000.twitchapi.api;

/**
 * Created by ysl3000
 */
public interface Stream {

    long getID();
    String getGameName();
    long getViewerCount();
    long getVideoHeight();
    long getAverageFPS();
    long getDelay();
    String getCreatedAt();
    boolean isLivePlaylist();
    PreviewImageUrls getPreview();
    Channel getChannel();
}

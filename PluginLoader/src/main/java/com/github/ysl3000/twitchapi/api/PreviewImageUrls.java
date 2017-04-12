package com.github.ysl3000.twitchapi.api;

/**
 * Created by ysl3000
 */
public interface PreviewImageUrls {
    String getUrlSmall();
    String getUrlMedium();
    String getUrlLarge();
    String getUrlWith(int width, int height);
}

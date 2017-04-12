package com.github.ysl3000.twitchapi.impl;

import com.github.ysl3000.twitchapi.api.PreviewImageUrls;
import org.json.simple.JSONObject;

/**
 * Created by ysl3000
 */
public class PreviewImageUrlsImpl implements PreviewImageUrls {
    private JSONObject jsonObject;

    public PreviewImageUrlsImpl(JSONObject jsonObject) {
        this.jsonObject = jsonObject != null ? (JSONObject) jsonObject.get("preview") : null;
    }

    public static boolean isAvailable(JSONObject jsonObject) {
        return jsonObject.get("preview") != null;
    }

    public String getUrlSmall() {
        return (String) jsonObject.get("small");
    }

    public String getUrlMedium() {
        return (String) jsonObject.get("medium");
    }

    public String getUrlLarge() {
        return (String) jsonObject.get("large");
    }

    public String getUrlWith(int width, int height) {
        return ((String) jsonObject.get("template")).
                replace("{width}", String.valueOf(width)).
                replace("{height}", String.valueOf(height));
    }
}

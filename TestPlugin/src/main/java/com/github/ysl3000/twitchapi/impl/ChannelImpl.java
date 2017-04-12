package com.github.ysl3000.twitchapi.impl;

import com.github.ysl3000.twitchapi.api.Channel;
import org.json.simple.JSONObject;

/**
 * Created by ysl3000
 */
public class ChannelImpl implements Channel {

    private JSONObject jsonObject;
    public ChannelImpl(JSONObject jsonObject) {
        this.jsonObject= jsonObject!=null?(JSONObject) jsonObject.get("channel"):null;
    }

    public boolean isMature() {
        return (Boolean) jsonObject.get("mature");
    }

    public boolean isPartner() {
        return (Boolean) jsonObject.get("partner");
    }

    public String getStatus() {
        return (String) jsonObject.get("status");
    }

    public String getBroadcasterLanguage() {
        return (String) jsonObject.get("broadcaster_language");
    }

    public String getDisplayname() {
        return (String) jsonObject.get("display_name");
    }

    public String getGameName() {
        return (String) jsonObject.get("game");
    }

    public String getLanguage() {
        return (String) jsonObject.get("language");
    }

    public long getID() {
        return (Long) jsonObject.get("_id");
    }

    public String getStreamersName() {
        return (String) jsonObject.get("name");
    }

    public String getCreatedAt() {
        return (String) jsonObject.get("created_at");
    }

    public String getUpdatedAt() {
        return (String) jsonObject.get("updated_at");
    }

    public JSONObject unsafe(){
        return (JSONObject) jsonObject.clone();
    }

    public static boolean isAvailable(JSONObject jsonObject){
        return jsonObject.get("channel")!=null;
    }
}

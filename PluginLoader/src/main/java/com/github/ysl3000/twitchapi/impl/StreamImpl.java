package com.github.ysl3000.twitchapi.impl;

import com.github.ysl3000.twitchapi.api.Channel;
import com.github.ysl3000.twitchapi.api.PreviewImageUrls;
import com.github.ysl3000.twitchapi.api.Stream;
import org.json.simple.JSONObject;

/**
 * Created by ysl3000 on 30.12.16.
 */
public class StreamImpl implements Stream {

    private JSONObject jsonObject;

    private PreviewImageUrls previewImageUrls;
    private Channel channel;

    public StreamImpl(JSONObject jsonObject) {
        this.jsonObject = (JSONObject) jsonObject.get("stream");
        this.previewImageUrls= PreviewImageUrlsImpl.isAvailable(this.jsonObject)?new PreviewImageUrlsImpl(this.jsonObject):null;
        this.channel = ChannelImpl.isAvailable(this.jsonObject)?new ChannelImpl(this.jsonObject):null;
    }

    public long getID() {
        return (Long) this.jsonObject.get("_id");
    }

    public String getGameName() {
        return (String) this.jsonObject.get("game");
    }

    public long getViewerCount() {
        return (Long) jsonObject.get("viewers");
    }

    public long getVideoHeight() {
        return (Long)jsonObject.get("video_height");
    }

    public long getAverageFPS() {
        return (Long)jsonObject.get("average_fps");
    }

    public long getDelay() {
        return (Long)jsonObject.get("delay");
    }

    public String getCreatedAt() {
        return (String) jsonObject.get("created_at");
    }

    public boolean isLivePlaylist() {
        return (Boolean)jsonObject.get("is_playlist");
    }

    public PreviewImageUrls getPreview() {
        return previewImageUrls;
    }

    public Channel getChannel() {
        return channel;
    }


    public static boolean isAvailable(JSONObject jsonObject){
        return jsonObject.get("stream")!=null;
    }
}

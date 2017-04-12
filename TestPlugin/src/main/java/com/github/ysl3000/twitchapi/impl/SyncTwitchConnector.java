package com.github.ysl3000.twitchapi.impl;

import com.github.ysl3000.twitchapi.api.StreamResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by ysl3000
 */
public class SyncTwitchConnector {

    private HttpClient httpClient = HttpClientBuilder.create().build();

    private String clientID;

    public SyncTwitchConnector(String clientID) {
        this.clientID = clientID;
    }

    public boolean isStreamingOnline(String twitchUserName) throws IOException, ParseException {
        return connectToStream(twitchUserName).getStream() != null;
    }

    public StreamResponse connectToStream(String twitchUserName) throws IOException, ParseException {

        HttpGet request = new HttpGet("https://api.twitch.tv/kraken/streams/" + twitchUserName + "?client_id=" + clientID);

        HttpResponse response = httpClient.execute(request);

        return new StreamResponseImpl(response);

    }
}

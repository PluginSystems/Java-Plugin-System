package com.github.ysl3000.twitchapi.impl;

import com.github.ysl3000.twitchapi.api.Stream;
import com.github.ysl3000.twitchapi.api.StreamResponse;
import org.apache.http.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ysl3000 on 30.12.16.
 */
public class StreamResponseImpl implements StreamResponse {


    private JSONObject jsonObject;
    private Stream stream = null;
    private HttpResponse httpResponse;

    public StreamResponseImpl(HttpResponse httpResponse) throws IOException, ParseException {
        this.httpResponse = httpResponse;

        if (getConnectionStatus() == 200) {

            JSONParser parser = new JSONParser();

            Object ob = parser.parse(new InputStreamReader(httpResponse.getEntity().getContent()));

            this.jsonObject = (JSONObject) ob;
            if(StreamImpl.isAvailable(jsonObject)){
                this.stream = new StreamImpl(jsonObject);
            }
        }
    }

    public Stream getStream() {
        return stream;
    }

    public int getConnectionStatus() {
        return httpResponse.getStatusLine().getStatusCode();
    }


}

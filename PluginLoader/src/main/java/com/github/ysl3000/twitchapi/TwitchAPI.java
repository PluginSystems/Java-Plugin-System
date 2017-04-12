package com.github.ysl3000.twitchapi;

import com.github.ysl3000.twitchapi.impl.SyncTwitchConnector;

/**
 * Created by ysl3000 on 30.12.16.
 */
public class TwitchAPI {


    private SyncTwitchConnector syncTwitchConnector;

    public TwitchAPI(String clientID) {
        this.syncTwitchConnector = new SyncTwitchConnector(clientID);

    }
    public SyncTwitchConnector getSyncTwitchConnector() {
        return syncTwitchConnector;
    }

}

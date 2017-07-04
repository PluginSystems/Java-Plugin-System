package com.github.ysl3000;


import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.twitchapi.TwitchAPI;

/**
 * Created by ysl3000
 */
public class TwitchTestPlugin implements IPlugin {
    @Override
    public String getPluginIdentity() {
        return "TwitchTestPlugin";
    }


    @Override
    public void onRegister() {

    }

    @Override
    public void onEnable() {

        TwitchAPI twitchAPI = new TwitchAPI("iiwsu6n5r8qiu1cug6zwupldjkfyn3");

        try {
            System.out.println("Spiel: " + twitchAPI
                    .getSyncTwitchConnector()
                    .connectToStream("yapyap30").getStream().getGameName());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDisable() {

    }
}

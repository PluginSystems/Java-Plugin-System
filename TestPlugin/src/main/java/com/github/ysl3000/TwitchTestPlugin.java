package com.github.ysl3000;


import com.github.ysl3000.pluginsystem.IPlugin;
import com.github.ysl3000.twitchapi.TwitchAPI;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by ysl3000
 */
public class TwitchTestPlugin implements IPlugin {
    @Override
    public String getPluginIdentity() {
        return "CustomTwitchTestPlugin2";
    }

    @Override
    public void onEnable() {

        TwitchAPI twitchAPI = new TwitchAPI("iiwsu6n5r8qiu1cug6zwupldjkfyn3");

        try {
            System.out.println("Spiel: " + twitchAPI
                    .getSyncTwitchConnector()
                    .connectToStream("deadpine").getStream().getGameName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Testing onEnable");

    }

    @Override
    public void onDisable() {

        System.out.println("Testing onDisable");
    }
}

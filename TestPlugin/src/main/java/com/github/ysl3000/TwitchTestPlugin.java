package com.github.ysl3000;


import com.github.ysl3000.pluginsystem.IPlugin;

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

        System.out.println("Testing onEnable");

    }

    @Override
    public void onDisable() {

        System.out.println("Testing onDisable");
    }
}

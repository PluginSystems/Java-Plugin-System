package com.github.ysl3000;

import com.github.ysl3000.api.ContextAPI;
import com.github.ysl3000.api.PluginStateChangeListener;
import com.github.ysl3000.impl.pluginsystem.IPlugin;

/**
 * Created by ysl3000
 */
public class Plugin2 implements IPlugin, PluginStateChangeListener {
    public String getPluginIdentity() {
        return "Plugin2";
    }

    public void onEnable() {

        ContextAPI.getSystem().registerPluginEnableListener(this,this);

    }

    public void onDisable() {

    }

    public void onPluginGetsEnabled(IPlugin plugin) {
        if(plugin instanceof Plugin3){
            System.out.println(plugin.getPluginIdentity());
        }
    }

    public void onPluginGetsDisabled(IPlugin plugin) {

    }
}

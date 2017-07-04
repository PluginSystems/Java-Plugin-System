package com.github.ysl3000;

import com.github.ysl3000.api.ContextAPI;
import com.github.ysl3000.api.PluginStateChangeListener;
import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.interfaces.PrintOther;

/**
 * Created by ysl3000
 */
public class Plugin2 implements IPlugin, PluginStateChangeListener {
    public String getPluginIdentity() {
        return "Plugin2";
    }

    @Override
    public void onRegister() {
        ContextAPI.getSystem().registerPluginEnableListener(this, this);
    }

    public void onEnable() {


        ContextAPI.getSystem().printPlugins();

        IPlugin plugin3 =ContextAPI.getSystem().getPlugin("Plugin3");

        if(plugin3 != null)
        if(plugin3 instanceof Plugin3){
            ((Plugin3) plugin3).printOther(this);
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onPluginGetsEnabled(IPlugin plugin) {

        if(plugin instanceof PrintOther){
            ((PrintOther) plugin).printOther(this);
        }


        if (plugin.getPluginIdentity().equalsIgnoreCase("Plugin3")) {
            System.out.println("Plugin "+plugin.getPluginIdentity()+" is there!");


        }
    }
    @Override
    public void onPluginGetsDisabled(IPlugin plugin) {

    }
}

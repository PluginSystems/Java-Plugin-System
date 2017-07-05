package com.github.ysl3000;

import com.github.ysl3000.api.IPlugin;

/**
 * Created by ysl3000
 */
public class Plugin3 implements IPlugin{
    public String getPluginIdentity() {
        return "Plugin3";
    }

    public void onRegister() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void printOther(IPlugin iPlugin){
        System.out.println("The identity of the other plugin is: "+iPlugin.getPluginIdentity());
    }

}

package com.github.ysl3000;

import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.interfaces.PrintOther;

/**
 * Created by ysl3000
 */
public class Plugin3 implements IPlugin, PrintOther{
    public String getPluginIdentity() {
        return "Plugin3";
    }

    public void onRegister() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    @Override
    public void printOther(IPlugin iPlugin){
        System.out.println("The identity of the other plugin is: "+iPlugin.getPluginIdentity());
    }

}

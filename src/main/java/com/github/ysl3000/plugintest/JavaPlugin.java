package com.github.ysl3000.plugintest;

import com.github.ysl3000.pluginsystem.IPlugin;

/**
 * Created by ysl3000
 */
public class JavaPlugin implements IPlugin {


    @Override
    public String getPluginIdentity() {
        return "MySpecialIdentity";
    }

    @Override
    public void onEnable() {
        System.out.println("Test enable");
    }

    @Override
    public void onDisable() {

        System.out.println("Test disable");
    }
}

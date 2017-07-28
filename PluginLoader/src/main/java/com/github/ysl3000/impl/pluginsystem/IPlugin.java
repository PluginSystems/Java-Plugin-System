package com.github.ysl3000.impl.pluginsystem;

public interface IPlugin {

    void onEnable();

    void onDisable();

    String getPluginIdentity();
}

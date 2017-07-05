package com.github.ysl3000.api;

/**
 * Created by ysl3000
 */
public  interface IPlugin {

    String getPluginIdentity();

    void onRegister();

    void onEnable();

    void onDisable();


}

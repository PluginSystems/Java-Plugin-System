package com.github.ysl3000.api;

/**
 * Created by ysl3000
 */
public interface PluginStateChangeListener {

    void onPluginGetsEnabled(IPlugin plugin);
    
    void onPluginGetsDisabled(IPlugin plugin);
}

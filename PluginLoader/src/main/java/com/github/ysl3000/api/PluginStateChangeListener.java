package com.github.ysl3000.api;

import com.github.ysl3000.impl.pluginsystem.IPlugin;

/**
 * Created by ysl3000
 */
public interface PluginStateChangeListener {

    void onPluginGetsEnabled(IPlugin plugin);
    
    void onPluginGetsDisabled(IPlugin plugin);
}

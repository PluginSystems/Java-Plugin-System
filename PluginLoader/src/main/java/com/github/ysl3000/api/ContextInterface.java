package com.github.ysl3000.api;

import com.github.ysl3000.impl.pluginsystem.IPlugin;

/**
 * Created by ysl3000
 */
public interface ContextInterface {
    IPlugin getPlugin(String name);

    void registerPluginEnableListener(IPlugin plugin, PluginStateChangeListener pluginStateChangeListener);

    void printPlugins();
}

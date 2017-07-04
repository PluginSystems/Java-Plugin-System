package com.github.ysl3000.api;

import com.github.ysl3000.impl.pluginsystem.IPlugin;

/**
 * Created by ysl3000
 */
public interface ContextInterface {
    <K extends IPlugin> K getPlugin(Class<K> clazz);

    void registerPluginEnableListener(IPlugin plugin,PluginStateChangeListener pluginStateChangeListener);
}

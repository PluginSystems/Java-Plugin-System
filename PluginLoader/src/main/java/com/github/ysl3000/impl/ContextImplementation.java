package com.github.ysl3000.impl;

import com.github.ysl3000.api.ContextInterface;
import com.github.ysl3000.api.PluginStateChangeListener;
import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;

/**
 * Created by ysl3000
 */
public class ContextImplementation implements ContextInterface {
    private PluginLoader<IPlugin> pluginLoader;


    public ContextImplementation(PluginLoader<IPlugin> pluginLoader) {
        this.pluginLoader = pluginLoader;
    }

    @Override
    public <K extends IPlugin> K getPlugin(Class<K> clazz) {
        return pluginLoader.getPlugin(clazz);
    }

    @Override
    public void registerPluginEnableListener(IPlugin plugin, PluginStateChangeListener pluginStateChangeListener) {
        this.pluginLoader.addListener(plugin, pluginStateChangeListener);
    }
}

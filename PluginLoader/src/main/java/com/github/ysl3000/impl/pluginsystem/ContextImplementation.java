package com.github.ysl3000.impl.pluginsystem;

import com.github.ysl3000.api.ContextInterface;
import com.github.ysl3000.api.IPlugin;
import com.github.ysl3000.api.PluginStateChangeListener;

/**
 * Created by ysl3000
 */
public class ContextImplementation implements ContextInterface {
    private PluginLoader<IPlugin> pluginLoader;


    public ContextImplementation(PluginLoader<IPlugin> pluginLoader) {
        this.pluginLoader = pluginLoader;
    }



    @Override
    public IPlugin getPlugin(String name) {
        return pluginLoader.getPlugin(name);
    }

    @Override
    public void registerPluginEnableListener(IPlugin plugin, PluginStateChangeListener pluginStateChangeListener) {
        this.pluginLoader.addListener(plugin, pluginStateChangeListener);
    }

    @Override
    public void printPlugins() {
        this.pluginLoader.plugins.values().forEach(p->System.out.println(p.getPluginIdentity()));
    }
}

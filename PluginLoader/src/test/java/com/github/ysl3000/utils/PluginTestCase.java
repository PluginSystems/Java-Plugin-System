package com.github.ysl3000.utils;

import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;

public abstract class PluginTestCase extends TestCase{

    protected PluginLoader<IPlugin> _pluginLoader;

    protected PluginTestCase(PluginLoader<IPlugin> pluginLoader)
    {
        _pluginLoader = pluginLoader;
    }


}

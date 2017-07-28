package com.github.ysl3000.benchmarks;

import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;
import com.github.ysl3000.impl.pluginsystem.PropertyPluginConfigLoader;
import com.github.ysl3000.impl.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.impl.pluginsystem.interfaces.PluginConfigLoader;
import com.github.ysl3000.impl.plugintest.ConsoleMessageLogger;
import com.github.ysl3000.utils.PluginTestCase;

import java.io.File;

/**
 * Created by ysl3000
 */
public abstract class AbstractBenchmark extends PluginTestCase {
    private MessageLogger messageLogger;
    private File folder;
    private PluginConfigLoader pluginConfigLoader;

    protected AbstractBenchmark(PluginLoader<IPlugin> pluginLoader) {
        super(pluginLoader);
    }

@Override
protected void SetUp() {

        this.messageLogger = new ConsoleMessageLogger();
        this.folder = new File("plugins");
        this.folder.mkdirs();
        this.pluginConfigLoader = new PropertyPluginConfigLoader();
        this._pluginLoader = new PluginLoader<>(messageLogger, folder, pluginConfigLoader);

    }

    @Override
    protected void TearDown() {
        this.messageLogger = null;
        this.folder = null;
        this.pluginConfigLoader = null;
        this._pluginLoader = null;
    }

}

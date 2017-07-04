package com.github.ysl3000;

import com.github.ysl3000.api.ContextAPI;
import com.github.ysl3000.api.ContextInterface;
import com.github.ysl3000.impl.pluginsystem.ContextImplementation;
import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;
import com.github.ysl3000.impl.pluginsystem.PropertyPluginConfigLoader;
import com.github.ysl3000.impl.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.impl.pluginsystem.interfaces.PluginConfigLoader;
import com.github.ysl3000.impl.plugintest.ConsoleMessageLogger;

import java.io.File;

/**
 * Created by ysl3000
 */
public class PreparePluginLoader {

    private MessageLogger messageLogger;
    private File folder;
    private PluginConfigLoader pluginConfigLoader;
    protected PluginLoader<IPlugin> pluginLoader;
    private ContextInterface contextInterface;


    public void setUp(){

        this.messageLogger = new ConsoleMessageLogger();
        this.folder = new File("plugins");
        this.folder.mkdirs();
        this.pluginConfigLoader = new PropertyPluginConfigLoader();
        this.pluginLoader = new PluginLoader<>(messageLogger, folder, pluginConfigLoader);
        this.contextInterface= new ContextImplementation(this.pluginLoader);
        ContextAPI.setImpl(this.contextInterface);

    }

    public void tearDown(){
        this.messageLogger = null;
        this.folder = null;
        this.pluginConfigLoader = null;
        this.pluginLoader = null;
        this.contextInterface=null;
    }

    public PluginLoader<IPlugin> getPluginLoader(){
        return pluginLoader;
    }


}

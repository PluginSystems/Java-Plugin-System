package com.github.ysl3000;

import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class TestContextInteraction {

    private PreparePluginLoader preparePluginLoader;


    @Before
    public void setUp() {
        this.preparePluginLoader = new PreparePluginLoader();
        this.preparePluginLoader.setUp();

    }

    @Test
    public void test() {
        PluginLoader<IPlugin> pluginLoader = this.preparePluginLoader.pluginLoader;

        pluginLoader.load();
        pluginLoader.enable();

        pluginLoader.disable();
        pluginLoader.unload();


    }


    @After
    public void tearDown() {
        this.preparePluginLoader.tearDown();
        this.preparePluginLoader = null;
    }
}

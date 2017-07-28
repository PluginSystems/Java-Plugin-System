package com.github.ysl3000.benchmarks;

import com.github.ysl3000.Plugin3;
import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;

public class ContextSwitchBenchmark extends AbstractBenchmark{


    private Plugin3 plugin3;

    public ContextSwitchBenchmark(PluginLoader<IPlugin> pluginLoader) {
        super(pluginLoader);
    }

    @Override
    protected void SetUp() {

        super.SetUp();
        _pluginLoader.load();
        _pluginLoader.enable();

        IPlugin plugin = _pluginLoader.getPlugin("Plugin3");
        if(plugin != null && plugin instanceof Plugin3){
            plugin3 = (Plugin3) plugin;
        }


    }

    @Override
    protected void RunTest(int currentCycle) {
        StartTimer();
        if(plugin3 != null){
            plugin3.test();
        }
        StopTimer();

        DefineBenchmarkPoint(currentCycle, "context_switch");

        ResetTimer();
    }

    @Override
    protected void TearDown() {
        _pluginLoader.disable();
        _pluginLoader.unload();

        super.TearDown();
    }
}

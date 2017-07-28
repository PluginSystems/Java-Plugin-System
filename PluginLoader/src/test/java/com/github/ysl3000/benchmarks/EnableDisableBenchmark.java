package com.github.ysl3000.benchmarks;

import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;

/**
 * Created by ysl3000
 */
public class EnableDisableBenchmark extends AbstractBenchmark {

    public EnableDisableBenchmark(PluginLoader<IPlugin> pluginLoader) {
        super(pluginLoader);
    }

    @Override
    public void SetUp(){
        super.SetUp();
        _pluginLoader.load();
    }


    @Override
    protected void TearDown(){
       _pluginLoader.unload();
        super.TearDown();

    }

    @Override
    protected void RunTest(int currentCycle) {
        StartTimer();
        _pluginLoader.enable();

        _pluginLoader.disable();

        StopTimer();

        DefineBenchmarkPoint(currentCycle, "Enable_Disable_Run");

        ResetTimer();
    }
}

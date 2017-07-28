package com.github.ysl3000.benchmarks;

import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;

/**
 * Created by ysl3000
 */
public class LoadingBenchmark extends AbstractBenchmark {


    public LoadingBenchmark(PluginLoader<IPlugin> pluginLoader) {
        super(pluginLoader);
    }

    @Override
    protected void RunTest(int currentCycle) {
        StartTimer();
        _pluginLoader.load();
        _pluginLoader.unload();
        StopTimer();

        DefineBenchmarkPoint(currentCycle, "loading_unloading");

        ResetTimer();
    }
}

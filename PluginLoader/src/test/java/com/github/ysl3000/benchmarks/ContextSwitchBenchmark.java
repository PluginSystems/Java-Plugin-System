package com.github.ysl3000.benchmarks;

import com.github.ysl3000.Plugin3;
import com.github.ysl3000.impl.pluginsystem.IPlugin;
import org.openjdk.jmh.annotations.Benchmark;

public class ContextSwitchBenchmark extends AbstractBenchmark{


    private Plugin3 plugin3;

    @Override
    public void setUp() {
        super.setUp();

        IPlugin plugin = preparePluginLoader.getPluginLoader().getPlugin("Plugin3");
        if(plugin != null && plugin instanceof Plugin3){
            plugin3 = (Plugin3) plugin;
        }

    }

    @Benchmark
    public void runBenchmark(){

        if(plugin3 != null){
            plugin3.test();
        }
    }

}

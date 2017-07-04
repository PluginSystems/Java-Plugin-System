package com.github.ysl3000.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by ysl3000
 */
public class EnableDisableBenchmark extends AbstractBenchmark {

    @Override
    public void setUp(){
        super.setUp();
        preparePluginLoader.getPluginLoader().load();
    }

    @Benchmark
    public void runEnableDisableBenchmark() {
        preparePluginLoader.getPluginLoader().enable();

        preparePluginLoader.getPluginLoader().disable();
    }
    @Override
    public void tearDown(){
        preparePluginLoader.getPluginLoader().unload();
        super.tearDown();

    }
}

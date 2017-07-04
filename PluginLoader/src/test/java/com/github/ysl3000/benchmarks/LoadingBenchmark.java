package com.github.ysl3000.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by ysl3000
 */
public class LoadingBenchmark extends AbstractBenchmark {

    @Benchmark
    public void benchmarkLoadUnload() {

        preparePluginLoader.getPluginLoader().load();
        preparePluginLoader.getPluginLoader().unload();

    }
}

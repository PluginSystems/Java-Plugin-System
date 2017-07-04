package com.github.ysl3000.benchmarks;

import com.github.ysl3000.PreparePluginLoader;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

/**
 * Created by ysl3000
 */
@State(Scope.Benchmark)
public class AbstractBenchmark {

    protected PreparePluginLoader preparePluginLoader;

    @Setup
    public void setUp() {
        preparePluginLoader = new PreparePluginLoader();
        preparePluginLoader.setUp();

    }

    @TearDown
    public void tearDown() {
        preparePluginLoader.tearDown();
        preparePluginLoader=null;

    }

}

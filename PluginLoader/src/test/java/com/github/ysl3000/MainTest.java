package com.github.ysl3000;

import com.github.ysl3000.pluginsystem.IPlugin;
import com.github.ysl3000.pluginsystem.PluginLoader;
import com.github.ysl3000.pluginsystem.PropertyPluginConfigLoader;
import com.github.ysl3000.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.pluginsystem.interfaces.PluginConfigLoader;
import com.github.ysl3000.plugintest.ConsoleMessageLogger;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by ysl3000
 */
@State(Scope.Benchmark)
public class MainTest {


    private MessageLogger messageLogger;

    private File folder;
    private PluginConfigLoader pluginConfigLoader;

    private PluginLoader<IPlugin> pluginLoader;

    @Test
    public void testLoader() throws RunnerException {
        Options opt = new OptionsBuilder()
                // Specify which benchmarks to run.
                // You can be more specific if you'd like to run only one benchmark per test.
                .include(this.getClass().getName() + ".*")
                // Set the following options as needed
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(2)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(10)
                .threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();

    }

    @Setup
    public void setUp() {
        this.messageLogger = new ConsoleMessageLogger();
        this.folder = new File("plugins");
        this.folder.mkdirs();
        this.pluginConfigLoader = new PropertyPluginConfigLoader();
        this.pluginLoader = new PluginLoader<>(messageLogger, folder, pluginConfigLoader);
    }


    @Benchmark
    public void contextSwitchingBenchmark() {

        //todo define method that interacts with api that interacts with plugin

    }


    @Benchmark
    public void runBenchmark() {

        pluginLoader.load();

        //pluginLoader.enable();

        //pluginLoader.disable();

    }

    @Benchmark
    public void benchmarkLoadUnload() {

        pluginLoader.load();
        pluginLoader.unload();

    }


    @Benchmark
    public void runBenchmarkTwitch() {

        pluginLoader.load();

        pluginLoader.enable();

        pluginLoader.disable();

    }

    @TearDown
    public void tearDown() {
        this.messageLogger = null;
        this.folder = null;
        this.pluginConfigLoader = null;
        this.pluginLoader = null;
    }
}

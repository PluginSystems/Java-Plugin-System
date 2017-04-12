package com.github.ysl3000;

import com.github.ysl3000.pluginsystem.IPlugin;
import com.github.ysl3000.pluginsystem.PluginLoader;
import com.github.ysl3000.pluginsystem.PropertyPluginConfigLoader;
import com.github.ysl3000.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.pluginsystem.interfaces.PluginConfigLoader;
import com.github.ysl3000.plugintest.ConsoleMessageLogger;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
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
public class MainTest {



    @Test
    public void testLoader() throws RunnerException {

        Options opt = new OptionsBuilder()
                // Specify which benchmarks to run.
                // You can be more specific if you'd like to run only one benchmark per test.
                .include(this.getClass().getName() + ".*")
                // Set the following options as needed
                .mode (Mode.AverageTime)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(2)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(100)
                .threads(8)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();




    }

    @Benchmark
    public void runBenchmark(){

        MessageLogger messageLogger = new ConsoleMessageLogger();
        File folder = new File("plugins");
        folder.mkdirs();

        PluginConfigLoader pluginConfigLoader = new PropertyPluginConfigLoader();

        PluginLoader<IPlugin> pluginLoader = new PluginLoader<>(messageLogger,folder,pluginConfigLoader);

        pluginLoader.load();

        //pluginLoader.enable();

        //pluginLoader.disable();

    }

}

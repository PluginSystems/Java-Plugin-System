package com.github.ysl3000;

import com.github.ysl3000.benchmarks.ContextSwitchBenchmark;
import com.github.ysl3000.benchmarks.EnableDisableBenchmark;
import com.github.ysl3000.benchmarks.LoadingBenchmark;
import com.github.ysl3000.impl.pluginsystem.IPlugin;
import com.github.ysl3000.impl.pluginsystem.PluginLoader;
import com.github.ysl3000.impl.pluginsystem.PropertyPluginConfigLoader;
import com.github.ysl3000.impl.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.impl.pluginsystem.interfaces.PluginConfigLoader;
import com.github.ysl3000.impl.plugintest.ConsoleMessageLogger;
import com.github.ysl3000.utils.TestCase;
import org.junit.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ysl3000
 */
public class MainTest {

    @Test
    public void testLoader() throws RunnerException {

        MessageLogger m = new ConsoleMessageLogger();


        PluginConfigLoader propertyPluginConfigLoader = new PropertyPluginConfigLoader();


        PluginLoader<IPlugin> pluginLoader = new PluginLoader<IPlugin>(m, new File("./plugins"), propertyPluginConfigLoader);

        List<TestCase> stats = new ArrayList<>();

        stats.add(new LoadingBenchmark(pluginLoader));
        stats.add(new EnableDisableBenchmark(pluginLoader));
        stats.add(new ContextSwitchBenchmark(pluginLoader));

        // 10, 50, 70, 100, 250
        int[] count = {5000, 10000, 50000};

        long currentTimeMillis = System.currentTimeMillis();


        for (int cycle : count) {

            stats.forEach(testCase -> {
                testCase.RunTestFully(cycle);
            });


            stats.forEach(finishedTest ->
            {
                FileOutputStream streamWriter = null;
                try {
                    streamWriter = new FileOutputStream("./results_" + cycle + "_" + finishedTest.GetName() + "_nanoseconds_" + currentTimeMillis + ".csv");

                    finishedTest.PrintStats(streamWriter);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        streamWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            streamWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }


            });
        }

        System.out.println("Test finished");

    }
}

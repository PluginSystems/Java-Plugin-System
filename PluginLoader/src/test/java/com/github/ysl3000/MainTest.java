package com.github.ysl3000;

import org.junit.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

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
                .include(this.getClass().getPackage().getName() + ".*")
                // Set the following options as needed
                .mode(Mode.SampleTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .warmupIterations(10)
                .measurementTime(TimeValue.nanoseconds(1))
                // 5000 10000 50000
                .measurementIterations(50000)
                .threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();

    }
}

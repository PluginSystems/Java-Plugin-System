package com.github.ysl3000.utils;


import com.google.common.base.Stopwatch;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class TestCase {

    private Stopwatch _stopwatch;
    private List<BenchmarkRun> _benchmarkRuns;

    private String className;

    public TestCase() {
        className = this.getClass().getName();
        _stopwatch = Stopwatch.createUnstarted();

    }


    public void RunTestFully(int cycles) {
        _benchmarkRuns = new ArrayList<BenchmarkRun>(cycles);
        SetUp();
        for (int i = 0; i < cycles; i++) {
            _benchmarkRuns.add(new BenchmarkRun());
            RunTest(i);
        }
        TearDown();
    }

    protected abstract void SetUp();

    protected abstract void RunTest(int currentCycle);

    public String GetName() {
        return className;
    }

    protected abstract void TearDown();


    public void PrintStats(OutputStream writer) {
        for (BenchmarkRun benbBenchmarkRun : _benchmarkRuns) {
            benbBenchmarkRun.PrintStats(writer);
        }
    }

    protected void StartTimer() {
        _stopwatch.start();
    }

    protected void StopTimer() {

        _stopwatch.stop();
    }

    protected void ResetTimer() {
        _stopwatch = Stopwatch.createUnstarted();
    }

    protected void DefineBenchmarkPoint(int cycle, String benchmarkPointName) {
        _benchmarkRuns.get(cycle).DefineBenchmarkPoint(benchmarkPointName, GetElapsedNanos());
    }

    protected void DefineBenchmarkPoint(int cycle, String benchmarkPointName, int run) {
        _benchmarkRuns.get(cycle).DefineBenchmarkPoint(benchmarkPointName + "_" + (cycle + 1) + "_" + (run + 1), GetElapsedNanos());
    }

    private long GetElapsedMicros() {
        return _stopwatch.elapsed(TimeUnit.MICROSECONDS);
    }

    private long GetElapsedNanos() {
        return _stopwatch.elapsed(TimeUnit.NANOSECONDS);
    }


}

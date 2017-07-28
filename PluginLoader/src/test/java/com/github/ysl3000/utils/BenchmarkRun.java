package com.github.ysl3000.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BenchmarkRun {


    private Map<String, Double> stats = new ConcurrentHashMap<>();


    public void DefineBenchmarkPoint(String benchmarkPointName, double microsElapsed)
    {
        stats.put(benchmarkPointName, microsElapsed);
    }

    public void DefineBenchmarkPoint(String benchmarkPointName, int run, double microsElapsed)
    {
        stats.put(benchmarkPointName + run, microsElapsed);
    }

    public List<String> GetStatsCommaSeperatedList() {
        return stats.entrySet().parallelStream().map((kv) -> kv.getKey() + "; " + kv.getValue()).collect(Collectors.toList());
    }

    public void PrintStats(OutputStream writer)
    {
        for(String stat : GetStatsCommaSeperatedList())
        {
            try {
                writer.write(stat.getBytes());
                writer.write("\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

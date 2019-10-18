package com.imc.workshop.integrationtest;


import com.imc.workshop.datamodel.Covariance;
import com.imc.workshop.datamodel.MarketEvent;
import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.util.*;

public class FullIntegrationBenchmark
{
    @State(Scope.Benchmark)
    public static class BenchmarkState
    {
        public static final List<Covariance> COVARIANCES = Covariances.getCovariances();
        public List<MarketEvent> theMarketEvents;

        public BenchmarkState()
        {
            try
            {
                theMarketEvents = LimitsGenerator.loadMarketEvents(LimitsGenerator.MARKET_EVENTS_FILENAME);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @BenchmarkMode(value = Mode.AverageTime)
    public void benchmark(BenchmarkState aBenchmarkState) {
        LimitsGenerator.replayMarketEvents(aBenchmarkState.theMarketEvents, BenchmarkState.COVARIANCES);
    }
}

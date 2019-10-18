package com.imc.workshop.integrationtest;


import com.imc.workshop.datamodel.Covariance;
import com.imc.workshop.datamodel.MarketEvent;
import com.imc.workshop.datamodel.PositionLimits;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.List;

public class FullIntegrationBenchmark {
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public static final List<Covariance> COVARIANCES = Covariances.getCovariances();
        public List<MarketEvent> theMarketEvents;

        public BenchmarkState() {
            try {
                theMarketEvents = LimitsGenerator.loadMarketEvents(LimitsGenerator.MARKET_EVENTS_FILENAME);
            } catch (IOException aE) {
                aE.printStackTrace();
            }
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @BenchmarkMode(value = Mode.AverageTime)
    public void benchmark(BenchmarkState aBenchmarkState) {
        final List<PositionLimits> positionLimits = LimitsGenerator.replayMarketEvents(aBenchmarkState.theMarketEvents, BenchmarkState.COVARIANCES);
        final PositionLimits finalLimits = positionLimits.get(positionLimits.size() - 1);
        if (finalLimits.getLastSequenceNumber() != 37748
                || !finalLimits.getInstrument().equals("AMZN")
                || Math.abs(finalLimits.getMaxAllowedSellVolume() - 69702.4146234835) > 10e-12) {
            throw new RuntimeException("Results are not valid!! Benchmark result will not be used");
        }
    }
}

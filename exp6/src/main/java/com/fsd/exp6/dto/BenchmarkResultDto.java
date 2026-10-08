package com.fsd.exp6.dto;

import java.io.Serializable;

public class BenchmarkResultDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String benchmarkName;
    private String description;
    private long unoptimizedExecutionTimeMs;
    private long optimizedExecutionTimeMs;
    private int unoptimizedQueryCount;
    private int optimizedQueryCount;
    private double latencyReductionPercentage;
    private String recommendation;

    public BenchmarkResultDto() {}

    public BenchmarkResultDto(String benchmarkName, String description, long unoptimizedExecutionTimeMs, long optimizedExecutionTimeMs, int unoptimizedQueryCount, int optimizedQueryCount, double latencyReductionPercentage, String recommendation) {
        this.benchmarkName = benchmarkName;
        this.description = description;
        this.unoptimizedExecutionTimeMs = unoptimizedExecutionTimeMs;
        this.optimizedExecutionTimeMs = optimizedExecutionTimeMs;
        this.unoptimizedQueryCount = unoptimizedQueryCount;
        this.optimizedQueryCount = optimizedQueryCount;
        this.latencyReductionPercentage = latencyReductionPercentage;
        this.recommendation = recommendation;
    }

    public String getBenchmarkName() { return benchmarkName; }
    public void setBenchmarkName(String benchmarkName) { this.benchmarkName = benchmarkName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getUnoptimizedExecutionTimeMs() { return unoptimizedExecutionTimeMs; }
    public void setUnoptimizedExecutionTimeMs(long unoptimizedExecutionTimeMs) { this.unoptimizedExecutionTimeMs = unoptimizedExecutionTimeMs; }

    public long getOptimizedExecutionTimeMs() { return optimizedExecutionTimeMs; }
    public void setOptimizedExecutionTimeMs(long optimizedExecutionTimeMs) { this.optimizedExecutionTimeMs = optimizedExecutionTimeMs; }

    public int getUnoptimizedQueryCount() { return unoptimizedQueryCount; }
    public void setUnoptimizedQueryCount(int unoptimizedQueryCount) { this.unoptimizedQueryCount = unoptimizedQueryCount; }

    public int getOptimizedQueryCount() { return optimizedQueryCount; }
    public void setOptimizedQueryCount(int optimizedQueryCount) { this.optimizedQueryCount = optimizedQueryCount; }

    public double getLatencyReductionPercentage() { return latencyReductionPercentage; }
    public void setLatencyReductionPercentage(double latencyReductionPercentage) { this.latencyReductionPercentage = latencyReductionPercentage; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }

    public static BenchmarkResultDtoBuilder builder() {
        return new BenchmarkResultDtoBuilder();
    }

    public static class BenchmarkResultDtoBuilder {
        private String benchmarkName;
        private String description;
        private long unoptimizedExecutionTimeMs;
        private long optimizedExecutionTimeMs;
        private int unoptimizedQueryCount;
        private int optimizedQueryCount;
        private double latencyReductionPercentage;
        private String recommendation;

        public BenchmarkResultDtoBuilder benchmarkName(String benchmarkName) { this.benchmarkName = benchmarkName; return this; }
        public BenchmarkResultDtoBuilder description(String description) { this.description = description; return this; }
        public BenchmarkResultDtoBuilder unoptimizedExecutionTimeMs(long unoptimizedExecutionTimeMs) { this.unoptimizedExecutionTimeMs = unoptimizedExecutionTimeMs; return this; }
        public BenchmarkResultDtoBuilder optimizedExecutionTimeMs(long optimizedExecutionTimeMs) { this.optimizedExecutionTimeMs = optimizedExecutionTimeMs; return this; }
        public BenchmarkResultDtoBuilder unoptimizedQueryCount(int unoptimizedQueryCount) { this.unoptimizedQueryCount = unoptimizedQueryCount; return this; }
        public BenchmarkResultDtoBuilder optimizedQueryCount(int optimizedQueryCount) { this.optimizedQueryCount = optimizedQueryCount; return this; }
        public BenchmarkResultDtoBuilder latencyReductionPercentage(double latencyReductionPercentage) { this.latencyReductionPercentage = latencyReductionPercentage; return this; }
        public BenchmarkResultDtoBuilder recommendation(String recommendation) { this.recommendation = recommendation; return this; }

        public BenchmarkResultDto build() {
            return new BenchmarkResultDto(benchmarkName, description, unoptimizedExecutionTimeMs, optimizedExecutionTimeMs, unoptimizedQueryCount, optimizedQueryCount, latencyReductionPercentage, recommendation);
        }
    }
}

package com.fsd.exp6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}

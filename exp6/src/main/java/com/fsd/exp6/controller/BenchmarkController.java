package com.fsd.exp6.controller;

import com.fsd.exp6.dto.BenchmarkResultDto;
import com.fsd.exp6.service.BenchmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/benchmark")
@RequiredArgsConstructor
public class BenchmarkController {

    private final BenchmarkService benchmarkService;

    @GetMapping("/n-plus-one")
    public ResponseEntity<BenchmarkResultDto> getNPlusOneBenchmark() {
        BenchmarkResultDto result = benchmarkService.benchmarkNPlusOneProblem();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/cache")
    public ResponseEntity<BenchmarkResultDto> getCacheBenchmark() {
        BenchmarkResultDto result = benchmarkService.benchmarkCacheLatency();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<BenchmarkResultDto>> getBenchmarkSummary() {
        BenchmarkResultDto nPlusOne = benchmarkService.benchmarkNPlusOneProblem();
        BenchmarkResultDto cache = benchmarkService.benchmarkCacheLatency();
        return ResponseEntity.ok(Arrays.asList(nPlusOne, cache));
    }
}

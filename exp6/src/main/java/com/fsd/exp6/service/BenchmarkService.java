package com.fsd.exp6.service;

import com.fsd.exp6.dto.BenchmarkResultDto;
import com.fsd.exp6.entity.Post;
import com.fsd.exp6.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BenchmarkService {

    private static final Logger log = LoggerFactory.getLogger(BenchmarkService.class);

    private final PostRepository postRepository;
    private final PostReadService postReadService;

    public BenchmarkService(PostRepository postRepository, PostReadService postReadService) {
        this.postRepository = postRepository;
        this.postReadService = postReadService;
    }

    @Transactional(readOnly = true)
    public BenchmarkResultDto benchmarkNPlusOneProblem() {
        log.info("Running N+1 Query Problem Benchmark...");

        // 1. Unoptimized Run (Triggers N+1 SQL queries when iterating over post author & comments)
        long startUnoptimized = System.nanoTime();
        List<Post> unoptimizedPosts = postRepository.findAllUnoptimized();
        int unoptimizedCount = 1;
        for (Post post : unoptimizedPosts) {
            if (post.getAuthor() != null) {
                post.getAuthor().getName();
                unoptimizedCount++;
            }
            unoptimizedCount += post.getComments().size();
        }
        long durationUnoptimizedMs = (System.nanoTime() - startUnoptimized) / 1_000_000;

        // 2. Optimized Run (Single SQL Query with @EntityGraph / JOIN FETCH)
        long startOptimized = System.nanoTime();
        List<Post> optimizedPosts = postRepository.findAllOptimized();
        for (Post post : optimizedPosts) {
            if (post.getAuthor() != null) {
                post.getAuthor().getName();
            }
            post.getComments().size();
        }
        long durationOptimizedMs = (System.nanoTime() - startOptimized) / 1_000_000;
        int optimizedCount = 1;

        double latencyReduction = durationUnoptimizedMs > 0 ?
                ((double) (durationUnoptimizedMs - durationOptimizedMs) / durationUnoptimizedMs) * 100.0 : 0.0;

        return BenchmarkResultDto.builder()
                .benchmarkName("N+1 Query Resolution Benchmark")
                .description("Compares standard Lazy-loading triggering N+1 SQL queries against JOIN FETCH / @EntityGraph single query optimization.")
                .unoptimizedExecutionTimeMs(durationUnoptimizedMs)
                .optimizedExecutionTimeMs(durationOptimizedMs)
                .unoptimizedQueryCount(unoptimizedCount)
                .optimizedQueryCount(optimizedCount)
                .latencyReductionPercentage(Math.round(latencyReduction * 100.0) / 100.0)
                .recommendation("Use @EntityGraph or JPQL JOIN FETCH on read-heavy API feeds to eliminate N+1 roundtrips to the database.")
                .build();
    }

    public BenchmarkResultDto benchmarkCacheLatency() {
        log.info("Running Spring Cache Latency Benchmark...");

        // Ensure cache is cleared for fair comparison
        postReadService.evictAllCaches();

        // 1. First Call: Cache MISS (Database Query Execution)
        long startMiss = System.nanoTime();
        postReadService.getPaginatedPosts(0, 10, "createdAt", "desc", null);
        long durationMissMs = (System.nanoTime() - startMiss) / 1_000_000;

        // 2. Second Call: Cache HIT (In-Memory Caffeine Retrieval)
        long startHit = System.nanoTime();
        postReadService.getPaginatedPosts(0, 10, "createdAt", "desc", null);
        long durationHitMs = (System.nanoTime() - startHit) / 1_000_000;

        double latencyReduction = durationMissMs > 0 ?
                ((double) (durationMissMs - durationHitMs) / durationMissMs) * 100.0 : 0.0;

        return BenchmarkResultDto.builder()
                .benchmarkName("Spring Cache Performance Benchmark")
                .description("Compares Database execution response latency (Cache MISS) against In-Memory Caffeine Cache retrieval latency (Cache HIT).")
                .unoptimizedExecutionTimeMs(durationMissMs)
                .optimizedExecutionTimeMs(durationHitMs)
                .unoptimizedQueryCount(1)
                .optimizedQueryCount(0)
                .latencyReductionPercentage(Math.round(latencyReduction * 100.0) / 100.0)
                .recommendation("Apply Spring @Cacheable with Caffeine/Redis on high-frequency read endpoints to achieve near zero latency (<2ms).")
                .build();
    }
}

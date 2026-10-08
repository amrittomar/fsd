package com.fsd.exp6.service;

import com.fsd.exp6.dto.AnalyticsDashboardDto;
import com.fsd.exp6.dto.CategoryStatsDto;
import com.fsd.exp6.dto.PostResponseDto;
import com.fsd.exp6.repository.CommentRepository;
import com.fsd.exp6.repository.PostRepository;
import com.fsd.exp6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticsService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "analytics-dashboard", key = "'dashboard_metrics'")
    public AnalyticsDashboardDto getDashboardMetrics() {
        log.info("CACHE MISS: Computing analytics dashboard metrics via DB SQL aggregations...");

        long totalUsers = userRepository.count();
        long totalPosts = postRepository.count();
        long totalComments = commentRepository.count();
        long totalViews = postRepository.getTotalViewsCount();

        List<CategoryStatsDto> categoryStats = postRepository.getCategoryStatistics();

        List<PostResponseDto> topTrending = postRepository.findTop5ByOrderByViewsCountDesc().stream()
                .map(post -> PostResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .category(post.getCategory())
                        .viewsCount(post.getViewsCount())
                        .createdAt(post.getCreatedAt())
                        .authorName(post.getAuthor() != null ? post.getAuthor().getName() : "Anonymous")
                        .commentCount(post.getComments() != null ? post.getComments().size() : 0)
                        .build())
                .collect(Collectors.toList());

        return AnalyticsDashboardDto.builder()
                .totalUsers(totalUsers)
                .totalPosts(totalPosts)
                .totalComments(totalComments)
                .aggregateViews(totalViews)
                .categoryStats(categoryStats)
                .topTrendingPosts(topTrending)
                .build();
    }
}

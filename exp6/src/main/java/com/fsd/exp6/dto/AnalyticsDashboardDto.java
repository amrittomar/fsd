package com.fsd.exp6.dto;

import java.io.Serializable;
import java.util.List;

public class AnalyticsDashboardDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long totalUsers;
    private Long totalPosts;
    private Long totalComments;
    private Long aggregateViews;
    private List<CategoryStatsDto> categoryStats;
    private List<PostResponseDto> topTrendingPosts;

    public AnalyticsDashboardDto() {}

    public AnalyticsDashboardDto(Long totalUsers, Long totalPosts, Long totalComments, Long aggregateViews, List<CategoryStatsDto> categoryStats, List<PostResponseDto> topTrendingPosts) {
        this.totalUsers = totalUsers;
        this.totalPosts = totalPosts;
        this.totalComments = totalComments;
        this.aggregateViews = aggregateViews;
        this.categoryStats = categoryStats;
        this.topTrendingPosts = topTrendingPosts;
    }

    public Long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(Long totalUsers) { this.totalUsers = totalUsers; }

    public Long getTotalPosts() { return totalPosts; }
    public void setTotalPosts(Long totalPosts) { this.totalPosts = totalPosts; }

    public Long getTotalComments() { return totalComments; }
    public void setTotalComments(Long totalComments) { this.totalComments = totalComments; }

    public Long getAggregateViews() { return aggregateViews; }
    public void setAggregateViews(Long aggregateViews) { this.aggregateViews = aggregateViews; }

    public List<CategoryStatsDto> getCategoryStats() { return categoryStats; }
    public void setCategoryStats(List<CategoryStatsDto> categoryStats) { this.categoryStats = categoryStats; }

    public List<PostResponseDto> getTopTrendingPosts() { return topTrendingPosts; }
    public void setTopTrendingPosts(List<PostResponseDto> topTrendingPosts) { this.topTrendingPosts = topTrendingPosts; }

    public static AnalyticsDashboardDtoBuilder builder() {
        return new AnalyticsDashboardDtoBuilder();
    }

    public static class AnalyticsDashboardDtoBuilder {
        private Long totalUsers;
        private Long totalPosts;
        private Long totalComments;
        private Long aggregateViews;
        private List<CategoryStatsDto> categoryStats;
        private List<PostResponseDto> topTrendingPosts;

        public AnalyticsDashboardDtoBuilder totalUsers(Long totalUsers) { this.totalUsers = totalUsers; return this; }
        public AnalyticsDashboardDtoBuilder totalPosts(Long totalPosts) { this.totalPosts = totalPosts; return this; }
        public AnalyticsDashboardDtoBuilder totalComments(Long totalComments) { this.totalComments = totalComments; return this; }
        public AnalyticsDashboardDtoBuilder aggregateViews(Long aggregateViews) { this.aggregateViews = aggregateViews; return this; }
        public AnalyticsDashboardDtoBuilder categoryStats(List<CategoryStatsDto> categoryStats) { this.categoryStats = categoryStats; return this; }
        public AnalyticsDashboardDtoBuilder topTrendingPosts(List<PostResponseDto> topTrendingPosts) { this.topTrendingPosts = topTrendingPosts; return this; }

        public AnalyticsDashboardDto build() {
            return new AnalyticsDashboardDto(totalUsers, totalPosts, totalComments, aggregateViews, categoryStats, topTrendingPosts);
        }
    }
}

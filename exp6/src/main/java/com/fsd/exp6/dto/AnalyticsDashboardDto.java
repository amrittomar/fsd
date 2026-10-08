package com.fsd.exp6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsDashboardDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long totalUsers;
    private Long totalPosts;
    private Long totalComments;
    private Long aggregateViews;
    private List<CategoryStatsDto> categoryStats;
    private List<PostResponseDto> topTrendingPosts;
}

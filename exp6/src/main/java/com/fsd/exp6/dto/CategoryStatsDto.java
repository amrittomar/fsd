package com.fsd.exp6.dto;

import java.io.Serializable;

public class CategoryStatsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String category;
    private Long postCount;
    private Long totalViews;
    private Double avgViews;

    public CategoryStatsDto() {}

    public CategoryStatsDto(String category, Long postCount, Long totalViews, Double avgViews) {
        this.category = category;
        this.postCount = postCount;
        this.totalViews = totalViews;
        this.avgViews = avgViews;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Long getPostCount() { return postCount; }
    public void setPostCount(Long postCount) { this.postCount = postCount; }

    public Long getTotalViews() { return totalViews; }
    public void setTotalViews(Long totalViews) { this.totalViews = totalViews; }

    public Double getAvgViews() { return avgViews; }
    public void setAvgViews(Double avgViews) { this.avgViews = avgViews; }

    public static CategoryStatsDtoBuilder builder() {
        return new CategoryStatsDtoBuilder();
    }

    public static class CategoryStatsDtoBuilder {
        private String category;
        private Long postCount;
        private Long totalViews;
        private Double avgViews;

        public CategoryStatsDtoBuilder category(String category) { this.category = category; return this; }
        public CategoryStatsDtoBuilder postCount(Long postCount) { this.postCount = postCount; return this; }
        public CategoryStatsDtoBuilder totalViews(Long totalViews) { this.totalViews = totalViews; return this; }
        public CategoryStatsDtoBuilder avgViews(Double avgViews) { this.avgViews = avgViews; return this; }

        public CategoryStatsDto build() {
            return new CategoryStatsDto(category, postCount, totalViews, avgViews);
        }
    }
}

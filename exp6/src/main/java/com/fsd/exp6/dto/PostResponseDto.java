package com.fsd.exp6.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PostResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String category;
    private Long viewsCount;
    private LocalDateTime createdAt;
    private String authorName;
    private int commentCount;

    public PostResponseDto() {}

    public PostResponseDto(Long id, String title, String category, Long viewsCount, LocalDateTime createdAt, String authorName, int commentCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.viewsCount = viewsCount;
        this.createdAt = createdAt;
        this.authorName = authorName;
        this.commentCount = commentCount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Long getViewsCount() { return viewsCount; }
    public void setViewsCount(Long viewsCount) { this.viewsCount = viewsCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public int getCommentCount() { return commentCount; }
    public void setCommentCount(int commentCount) { this.commentCount = commentCount; }

    public static PostResponseDtoBuilder builder() {
        return new PostResponseDtoBuilder();
    }

    public static class PostResponseDtoBuilder {
        private Long id;
        private String title;
        private String category;
        private Long viewsCount;
        private LocalDateTime createdAt;
        private String authorName;
        private int commentCount;

        public PostResponseDtoBuilder id(Long id) { this.id = id; return this; }
        public PostResponseDtoBuilder title(String title) { this.title = title; return this; }
        public PostResponseDtoBuilder category(String category) { this.category = category; return this; }
        public PostResponseDtoBuilder viewsCount(Long viewsCount) { this.viewsCount = viewsCount; return this; }
        public PostResponseDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public PostResponseDtoBuilder authorName(String authorName) { this.authorName = authorName; return this; }
        public PostResponseDtoBuilder commentCount(int commentCount) { this.commentCount = commentCount; return this; }

        public PostResponseDto build() {
            return new PostResponseDto(id, title, category, viewsCount, createdAt, authorName, commentCount);
        }
    }
}

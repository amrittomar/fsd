package com.fsd.exp6.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class PostDetailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String content;
    private String category;
    private Long viewsCount;
    private LocalDateTime createdAt;
    private String authorName;
    private String authorEmail;
    private List<CommentDto> comments;

    public PostDetailDto() {}

    public PostDetailDto(Long id, String title, String content, String category, Long viewsCount, LocalDateTime createdAt, String authorName, String authorEmail, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.viewsCount = viewsCount;
        this.createdAt = createdAt;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.comments = comments;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Long getViewsCount() { return viewsCount; }
    public void setViewsCount(Long viewsCount) { this.viewsCount = viewsCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getAuthorEmail() { return authorEmail; }
    public void setAuthorEmail(String authorEmail) { this.authorEmail = authorEmail; }

    public List<CommentDto> getComments() { return comments; }
    public void setComments(List<CommentDto> comments) { this.comments = comments; }

    public static PostDetailDtoBuilder builder() {
        return new PostDetailDtoBuilder();
    }

    public static class PostDetailDtoBuilder {
        private Long id;
        private String title;
        private String content;
        private String category;
        private Long viewsCount;
        private LocalDateTime createdAt;
        private String authorName;
        private String authorEmail;
        private List<CommentDto> comments;

        public PostDetailDtoBuilder id(Long id) { this.id = id; return this; }
        public PostDetailDtoBuilder title(String title) { this.title = title; return this; }
        public PostDetailDtoBuilder content(String content) { this.content = content; return this; }
        public PostDetailDtoBuilder category(String category) { this.category = category; return this; }
        public PostDetailDtoBuilder viewsCount(Long viewsCount) { this.viewsCount = viewsCount; return this; }
        public PostDetailDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public PostDetailDtoBuilder authorName(String authorName) { this.authorName = authorName; return this; }
        public PostDetailDtoBuilder authorEmail(String authorEmail) { this.authorEmail = authorEmail; return this; }
        public PostDetailDtoBuilder comments(List<CommentDto> comments) { this.comments = comments; return this; }

        public PostDetailDto build() {
            return new PostDetailDto(id, title, content, category, viewsCount, createdAt, authorName, authorEmail, comments);
        }
    }
}

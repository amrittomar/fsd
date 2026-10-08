package com.fsd.exp6.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String text;
    private String authorName;
    private LocalDateTime createdAt;

    public CommentDto() {}

    public CommentDto(Long id, String text, String authorName, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.authorName = authorName;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static CommentDtoBuilder builder() {
        return new CommentDtoBuilder();
    }

    public static class CommentDtoBuilder {
        private Long id;
        private String text;
        private String authorName;
        private LocalDateTime createdAt;

        public CommentDtoBuilder id(Long id) { this.id = id; return this; }
        public CommentDtoBuilder text(String text) { this.text = text; return this; }
        public CommentDtoBuilder authorName(String authorName) { this.authorName = authorName; return this; }
        public CommentDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public CommentDto build() {
            return new CommentDto(id, text, authorName, createdAt);
        }
    }
}

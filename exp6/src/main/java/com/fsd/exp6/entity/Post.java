package com.fsd.exp6.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts", indexes = {
    @Index(name = "idx_post_category", columnList = "category"),
    @Index(name = "idx_post_created_at", columnList = "createdAt")
})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String category;

    private Long viewsCount;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Post() {}

    public Post(Long id, String title, String content, String category, Long viewsCount, LocalDateTime createdAt, User author, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.viewsCount = viewsCount;
        this.createdAt = createdAt;
        this.author = author;
        this.comments = comments != null ? comments : new ArrayList<>();
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

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public static class PostBuilder {
        private Long id;
        private String title;
        private String content;
        private String category;
        private Long viewsCount;
        private LocalDateTime createdAt;
        private User author;
        private List<Comment> comments = new ArrayList<>();

        public PostBuilder id(Long id) { this.id = id; return this; }
        public PostBuilder title(String title) { this.title = title; return this; }
        public PostBuilder content(String content) { this.content = content; return this; }
        public PostBuilder category(String category) { this.category = category; return this; }
        public PostBuilder viewsCount(Long viewsCount) { this.viewsCount = viewsCount; return this; }
        public PostBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public PostBuilder author(User author) { this.author = author; return this; }
        public PostBuilder comments(List<Comment> comments) { this.comments = comments; return this; }

        public Post build() {
            return new Post(id, title, content, category, viewsCount, createdAt, author, comments);
        }
    }
}

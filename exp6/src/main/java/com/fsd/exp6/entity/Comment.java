package com.fsd.exp6.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {}

    public Comment(Long id, String text, LocalDateTime createdAt, User author, Post post) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.author = author;
        this.post = post;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }

    public static CommentBuilder builder() {
        return new CommentBuilder();
    }

    public static class CommentBuilder {
        private Long id;
        private String text;
        private LocalDateTime createdAt;
        private User author;
        private Post post;

        public CommentBuilder id(Long id) { this.id = id; return this; }
        public CommentBuilder text(String text) { this.text = text; return this; }
        public CommentBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public CommentBuilder author(User author) { this.author = author; return this; }
        public CommentBuilder post(Post post) { this.post = post; return this; }

        public Comment build() {
            return new Comment(id, text, createdAt, author, post);
        }
    }
}

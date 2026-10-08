package com.fsd.exp6.service;

import com.fsd.exp6.dto.CommentDto;
import com.fsd.exp6.dto.PostDetailDto;
import com.fsd.exp6.dto.PostResponseDto;
import com.fsd.exp6.entity.Post;
import com.fsd.exp6.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class PostReadService {

    private static final Logger log = LoggerFactory.getLogger(PostReadService.class);

    private final PostRepository postRepository;

    public PostReadService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "posts-page", key = "'p_' + #page + '_s_' + #size + '_sb_' + #sortBy + '_sd_' + #sortDir + '_cat_' + (#category != null ? #category : 'all')")
    public Page<PostResponseDto> getPaginatedPosts(int page, int size, String sortBy, String sortDir, String category) {
        log.info("CACHE MISS: Fetching paginated posts from Database (page={}, size={}, category={})", page, size, category);

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> postPage;
        if (category != null && !category.trim().isEmpty()) {
            postPage = postRepository.findByCategory(category.toUpperCase(), pageable);
        } else {
            postPage = postRepository.findAll(pageable);
        }

        return postPage.map(this::mapToResponseDto);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "post-details", key = "#id")
    public PostDetailDto getPostDetailById(Long id) {
        log.info("CACHE MISS: Fetching post detail with id={} from Database", id);

        Post post = postRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));

        return PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .viewsCount(post.getViewsCount())
                .createdAt(post.getCreatedAt())
                .authorName(post.getAuthor() != null ? post.getAuthor().getName() : "Anonymous")
                .authorEmail(post.getAuthor() != null ? post.getAuthor().getEmail() : "")
                .comments(post.getComments().stream()
                        .map(c -> CommentDto.builder()
                                .id(c.getId())
                                .text(c.getText())
                                .authorName(c.getAuthor() != null ? c.getAuthor().getName() : "Anonymous")
                                .createdAt(c.getCreatedAt())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @CacheEvict(value = {"posts-page", "post-details", "analytics-dashboard"}, allEntries = true)
    public void evictAllCaches() {
        log.info("CACHE EVICTED: Evicted all posts and analytics caches.");
    }

    private PostResponseDto mapToResponseDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .category(post.getCategory())
                .viewsCount(post.getViewsCount())
                .createdAt(post.getCreatedAt())
                .authorName(post.getAuthor() != null ? post.getAuthor().getName() : "Anonymous")
                .commentCount(post.getComments() != null ? post.getComments().size() : 0)
                .build();
    }
}

package com.fsd.exp6.controller;

import com.fsd.exp6.dto.PostDetailDto;
import com.fsd.exp6.dto.PostResponseDto;
import com.fsd.exp6.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostReadController {

    private final PostReadService postReadService;

    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> getPaginatedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String category) {
        
        Page<PostResponseDto> posts = postReadService.getPaginatedPosts(page, size, sortBy, sortDir, category);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailDto> getPostById(@PathVariable Long id) {
        PostDetailDto postDetail = postReadService.getPostDetailById(id);
        return ResponseEntity.ok(postDetail);
    }

    @PostMapping("/cache/evict")
    public ResponseEntity<String> evictCaches() {
        postReadService.evictAllCaches();
        return ResponseEntity.ok("All read API caches evicted successfully.");
    }
}

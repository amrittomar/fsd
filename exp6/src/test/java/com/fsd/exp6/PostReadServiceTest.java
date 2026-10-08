package com.fsd.exp6;

import com.fsd.exp6.dto.PostDetailDto;
import com.fsd.exp6.dto.PostResponseDto;
import com.fsd.exp6.entity.Post;
import com.fsd.exp6.entity.User;
import com.fsd.exp6.repository.PostRepository;
import com.fsd.exp6.service.PostReadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostReadServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostReadService postReadService;

    private Post samplePost;
    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = User.builder()
                .id(1L)
                .username("testuser")
                .name("Test User")
                .email("test@example.com")
                .build();

        samplePost = Post.builder()
                .id(100L)
                .title("Test Post")
                .content("Test Content")
                .category("TECH")
                .viewsCount(500L)
                .createdAt(LocalDateTime.now())
                .author(sampleUser)
                .comments(new ArrayList<>())
                .build();
    }

    @Test
    void testGetPaginatedPostsSuccess() {
        Page<Post> page = new PageImpl<>(Collections.singletonList(samplePost));
        when(postRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<PostResponseDto> result = postReadService.getPaginatedPosts(0, 10, "createdAt", "desc", null);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Post", result.getContent().get(0).getTitle());
        assertEquals("Test User", result.getContent().get(0).getAuthorName());
        verify(postRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testGetPostDetailByIdSuccess() {
        when(postRepository.findByIdWithDetails(100L)).thenReturn(Optional.of(samplePost));

        PostDetailDto result = postReadService.getPostDetailById(100L);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Test Post", result.getTitle());
        assertEquals("test@example.com", result.getAuthorEmail());
        verify(postRepository, times(1)).findByIdWithDetails(100L);
    }
}

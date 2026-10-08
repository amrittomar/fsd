package com.fsd.exp6;

import com.fsd.exp6.dto.PostDetailDto;
import com.fsd.exp6.dto.PostResponseDto;
import com.fsd.exp6.service.PostReadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostReadService postReadService;

    @Test
    void testGetPaginatedPostsEndpoint() throws Exception {
        PostResponseDto dto = PostResponseDto.builder()
                .id(1L)
                .title("Spring Boot Caching")
                .category("TECH")
                .viewsCount(1200L)
                .createdAt(LocalDateTime.now())
                .authorName("Jane Doe")
                .commentCount(3)
                .build();

        when(postReadService.getPaginatedPosts(anyInt(), anyInt(), anyString(), anyString(), nullable(String.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(dto)));

        mockMvc.perform(get("/api/posts?page=0&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Spring Boot Caching"))
                .andExpect(jsonPath("$.content[0].authorName").value("Jane Doe"));
    }

    @Test
    void testGetPostByIdEndpoint() throws Exception {
        PostDetailDto detailDto = PostDetailDto.builder()
                .id(1L)
                .title("Query Optimization")
                .content("Avoid N+1 queries using EntityGraph")
                .category("TECH")
                .viewsCount(2500L)
                .authorName("John Smith")
                .authorEmail("john@example.com")
                .comments(new ArrayList<>())
                .build();

        when(postReadService.getPostDetailById(1L)).thenReturn(detailDto);

        mockMvc.perform(get("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Query Optimization"))
                .andExpect(jsonPath("$.authorEmail").value("john@example.com"));
    }
}

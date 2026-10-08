package com.fsd.exp6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String category;
    private Long viewsCount;
    private LocalDateTime createdAt;
    private String authorName;
    private int commentCount;
}

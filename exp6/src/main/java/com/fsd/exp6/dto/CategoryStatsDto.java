package com.fsd.exp6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryStatsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String category;
    private Long postCount;
    private Long totalViews;
    private Double avgViews;
}

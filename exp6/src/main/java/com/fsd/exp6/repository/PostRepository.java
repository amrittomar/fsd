package com.fsd.exp6.repository;

import com.fsd.exp6.dto.CategoryStatsDto;
import com.fsd.exp6.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Filtered page fetch with author relation
    @EntityGraph(attributePaths = {"author"})
    Page<Post> findByCategory(String category, Pageable pageable);

    // Default paginated fetch with EntityGraph for author
    @Override
    @EntityGraph(attributePaths = {"author"})
    Page<Post> findAll(Pageable pageable);

    // Optimized single fetch solving N+1 problem for post details with author and comments
    @Query("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.comments c LEFT JOIN FETCH c.author WHERE p.id = :id")
    Optional<Post> findByIdWithDetails(@Param("id") Long id);

    // Unoptimized fetch triggering N+1 query problem for demonstration
    @Query("SELECT p FROM Post p")
    List<Post> findAllUnoptimized();

    // Optimized batch fetch using EntityGraph
    @EntityGraph(attributePaths = {"author", "comments"})
    @Query("SELECT DISTINCT p FROM Post p")
    List<Post> findAllOptimized();

    // Database aggregation query for analytics dashboard metrics by category
    @Query("SELECT new com.fsd.exp6.dto.CategoryStatsDto(p.category, COUNT(p), SUM(p.viewsCount), AVG(p.viewsCount)) " +
           "FROM Post p GROUP BY p.category ORDER BY SUM(p.viewsCount) DESC")
    List<CategoryStatsDto> getCategoryStatistics();

    // Top trending posts ordered by views
    @EntityGraph(attributePaths = {"author"})
    List<Post> findTop5ByOrderByViewsCountDesc();

    // Aggregate total views count across all posts
    @Query("SELECT COALESCE(SUM(p.viewsCount), 0) FROM Post p")
    Long getTotalViewsCount();
}

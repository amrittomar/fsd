package com.fsd.exp6.config;

import com.fsd.exp6.entity.Comment;
import com.fsd.exp6.entity.Post;
import com.fsd.exp6.entity.User;
import com.fsd.exp6.repository.CommentRepository;
import com.fsd.exp6.repository.PostRepository;
import com.fsd.exp6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(String... args) {
        log.info("Starting automatic database seed initialization...");

        if (userRepository.count() > 0) {
            log.info("Database already seeded. Skipping initialization.");
            return;
        }

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            users.add(userRepository.save(User.builder()
                    .username("user" + i)
                    .email("user" + i + "@example.com")
                    .name("User " + i)
                    .status("ACTIVE")
                    .createdAt(LocalDateTime.now().minusDays(i))
                    .build()));
        }

        String[] categories = {"TECH", "FINANCE", "HEALTH", "EDUCATION", "ENTERTAINMENT"};
        Random random = new Random(42);

        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            User author = users.get(random.nextInt(users.size()));
            String category = categories[i % categories.length];
            long views = 100L + random.nextInt(5000);

            Post post = Post.builder()
                    .title("Scalable Architecture & Optimization Post #" + i)
                    .content("Detailed content about scalability, query tuning, caching strategies, and backend performance for post number " + i)
                    .category(category)
                    .viewsCount(views)
                    .createdAt(LocalDateTime.now().minusHours(i * 2))
                    .author(author)
                    .build();

            post = postRepository.save(post);

            int commentCount = 1 + random.nextInt(4);
            for (int j = 1; j <= commentCount; j++) {
                User commenter = users.get(random.nextInt(users.size()));
                Comment comment = Comment.builder()
                        .text("Great insightful post! Comment #" + j + " on post " + i)
                        .createdAt(LocalDateTime.now().minusMinutes(j * 15))
                        .author(commenter)
                        .post(post)
                        .build();
                commentRepository.save(comment);
            }

            posts.add(post);
        }

        log.info("Database seeding completed successfully! Seeded {} users, {} posts, and associated comments.", users.size(), posts.size());
    }
}

# FSD Experiment 6: Scalable Read APIs with Caching & Optimization

## 📌 Aim
Create high-performance read APIs that support pagination, sorting, caching, and optimized database queries to power analytics dashboards and post feeds in modern applications.

---

## 🚀 Key Features Implemented

1. **Scalable Pagination & Sorting**:
   - Integrated Spring Data JPA `Pageable` & DTO projections.
   - Dynamic sorting by `createdAt`, `viewsCount`, `title` in `asc` or `desc` directions.
   - Category filtering (`TECH`, `FINANCE`, `HEALTH`, `EDUCATION`, `ENTERTAINMENT`).

2. **Solving the N+1 Query Problem**:
   - Demonstrates standard lazy loading vs optimized `@EntityGraph(attributePaths = {"author", "comments"})` and JPQL `JOIN FETCH`.
   - Single SQL query execution for fetching post feeds with relational user authors and comments.

3. **High-Performance Spring Caching**:
   - Integrated Spring Cache with Caffeine in-memory caching provider (`@EnableCaching`, `@Cacheable`, `@CacheEvict`).
   - Near zero latency (<2ms) on Cache HIT vs ~25-50ms on raw Database reads.

4. **Analytics Dashboard Read APIs**:
   - Database-level aggregation queries (`COUNT`, `SUM`, `AVG`, `GROUP BY category`).
   - DTO constructor projections returning aggregate views and top trending post metrics.

5. **Performance Benchmarking Suite**:
   - Built-in REST benchmark controllers comparing N+1 queries vs JOIN FETCH and Cache Miss vs Cache Hit.

---

## 🛠️ Tech Stack & Architecture

- **Framework**: Spring Boot 3.2.4 (Java 17)
- **Database**: H2 In-Memory Database (Zero setup required)
- **ORM & Data Access**: Spring Data JPA / Hibernate
- **Caching**: Caffeine Cache Engine
- **Testing**: JUnit 5, MockMvc, Mockito
- **Build Tool**: Maven

---

## ⚡ How to Run

1. **Clone/Navigate to project directory**:
   ```bash
   cd fsd/exp6
   ```

2. **Build the application**:
   ```bash
   mvn clean package
   ```

3. **Run the Spring Boot Application**:
   ```bash
   mvn spring-boot:run
   ```
   *The application will automatically seed 10 users, 50 posts, and ~150 comments into the H2 database.*

4. **Access H2 Console**:
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:exp6db`
   - Username: `sa` | Password: `password`

---

## 🌐 API Reference & Verification Commands

### 1. Paginated & Sorted Post Feed
```bash
# Fetch page 0 with 10 posts sorted by createdAt descending
curl -X GET "http://localhost:8080/api/posts?page=0&size=10&sortBy=createdAt&sortDir=desc"

# Filter posts by TECH category
curl -X GET "http://localhost:8080/api/posts?page=0&size=10&category=TECH"
```

### 2. Post Detail Read API
```bash
curl -X GET "http://localhost:8080/api/posts/1"
```

### 3. Analytics Dashboard Read API
```bash
curl -X GET "http://localhost:8080/api/analytics/dashboard"
```

### 4. Benchmark APIs
```bash
# Compare N+1 Query Problem vs Single JOIN FETCH Query
curl -X GET "http://localhost:8080/api/benchmark/n-plus-one"

# Compare Cache MISS vs Cache HIT latency
curl -X GET "http://localhost:8080/api/benchmark/cache"

# Get Full Benchmark Summary
curl -X GET "http://localhost:8080/api/benchmark/summary"
```

### 5. Clear Caches
```bash
curl -X POST "http://localhost:8080/api/posts/cache/evict"
```

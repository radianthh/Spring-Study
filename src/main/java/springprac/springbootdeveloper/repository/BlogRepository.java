package springprac.springbootdeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springprac.springbootdeveloper.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}

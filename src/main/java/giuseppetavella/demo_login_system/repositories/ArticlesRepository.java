package giuseppetavella.demo_login_system.repositories;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, UUID> {

    /**
     * Find articles of the given user.
     */
    Page<Article> findByUser(User user, Pageable pageable);

}

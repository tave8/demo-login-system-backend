package giuseppetavella.demo_login_system.repositories;

import giuseppetavella.demo_login_system.entities.ForgotPasswordCode;
import giuseppetavella.demo_login_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordCode, UUID> {

    /**
     * Get the last forgot password code of this user.
     */
    @Query("SELECT f FROM ForgotPasswordCode f WHERE f.user = :user ORDER BY f.createdAt DESC LIMIT 1")
    Optional<ForgotPasswordCode> getLastCodeByUser(@Param("user") User user);

    /**
     * Mark all codes of the given user, as unusable. 
     * Note: you should execute this query right BEFORE you add a new code.
     * If you execute this query after you add a new code, 
     * this newly added will be marked as unusable as well, 
     * which is not what we want.
     */
    @Modifying
    @Transactional
    @Query("UPDATE ForgotPasswordCode f SET f.usable = false WHERE f.user = :user")
    void markAllCodesAsUnusable(@Param("user") User user);

}

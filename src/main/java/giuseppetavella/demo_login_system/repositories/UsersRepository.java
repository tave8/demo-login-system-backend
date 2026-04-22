package giuseppetavella.demo_login_system.repositories;

import giuseppetavella.demo_login_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {

    /**
     * The user with the given email exists?
     */
    boolean existsByEmail(String email);
    
}

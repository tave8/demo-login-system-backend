package giuseppetavella.demo_login_system.repositories;

import giuseppetavella.demo_login_system.entities.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerificationCode, UUID> {
    
    
    
}

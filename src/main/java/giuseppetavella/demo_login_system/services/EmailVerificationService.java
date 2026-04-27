package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.repositories.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {
    
    @Autowired
    private EmailVerificationRepository emailVerificationRepository;
    
    
    
}

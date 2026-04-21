package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.repositories.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {
    
    @Autowired
    private ArticlesRepository articlesRepository;
    
    
    
}

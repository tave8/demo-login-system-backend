package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.NotFoundException;
import giuseppetavella.demo_login_system.exceptions.UnauthorizedException;
import giuseppetavella.demo_login_system.repositories.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;
    
    @Autowired
    private UsersService usersService;

    /**
     * Find an article by ID.
     */
    public Article findById(UUID articleId) throws NotFoundException {
        return articlesRepository.findById(articleId).orElseThrow(() -> new NotFoundException(articleId, "article"));
    }

    /**
     * Add an article.
     * Checks if the user exists.
     */
    public Article addArticle(Article article) throws NotFoundException {
        boolean userExists = this.usersService.existsById(article.getUser().getUserId());
        if(!userExists) {
            throw new UnauthorizedException("The user associated with this article does not exist in DB.");
        }
        return this.articlesRepository.save(article);
    }
    

}

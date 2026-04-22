package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.NotFoundException;
import giuseppetavella.demo_login_system.exceptions.UnauthorizedException;
import giuseppetavella.demo_login_system.repositories.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * Get articles of the given user.
     */
    public Page<Article> findByUser(User user, int page, int size, String sortBy) {
        // / the size of each page (how many elements in each page)
        int finalSize = Math.min(10, size);
        // the page number
        int finalPage = Math.max(0, page);
        // page is the function that will get translated to SQL,
        // that will in turn filter the result set
        Pageable pageable = PageRequest.of(finalPage, finalSize, Sort.by(sortBy));
        return this.articlesRepository.findByUser(user, pageable);
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

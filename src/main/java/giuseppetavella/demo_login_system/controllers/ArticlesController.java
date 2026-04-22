package giuseppetavella.demo_login_system.controllers;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.payloads.in_request.NewArticleSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.ArticleToSendDTO;
import giuseppetavella.demo_login_system.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    
    @Autowired
    private ArticlesService articlesService;

    @PostMapping
    public ArticleToSendDTO addArticle(@RequestBody @Validated NewArticleSentDTO body,
                                       @AuthenticationPrincipal User currentUser) 
    {
        return new ArticleToSendDTO(
                this.articlesService.addArticle(body, currentUser)
        );
    }
    
    @GetMapping("/me")
    public Page<ArticleToSendDTO> findOwnArticles(@AuthenticationPrincipal User currentUser, 
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "createdAt") String sortBy) 
    {
        Page<Article> pagedArticles = this.articlesService.findByUser(currentUser, page, size, sortBy);
        return pagedArticles.map(article -> new ArticleToSendDTO(article));
    }

}

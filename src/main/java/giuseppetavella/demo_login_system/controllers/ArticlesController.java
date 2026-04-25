package giuseppetavella.demo_login_system.controllers;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.payloads.in_request.NewArticleSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.UpdatedArticleSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.ArticleToSendDTO;
import giuseppetavella.demo_login_system.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    
    @Autowired
    private ArticlesService articlesService;

    /**
     * Add article as my profile.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleToSendDTO addOwnArticle(@RequestBody @Validated NewArticleSentDTO body,
                                       @AuthenticationPrincipal User currentUser) 
    {
        return new ArticleToSendDTO(
                this.articlesService.addOwnArticle(body, currentUser)
        );
    }

    /**
     * Get my articles.
     */
    @GetMapping("/")
    public Page<ArticleToSendDTO> findOwnArticles(@AuthenticationPrincipal User currentUser, 
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "createdAt") String sortBy) 
    {
        Page<Article> pagedArticles = this.articlesService.findByUser(currentUser, page, size, sortBy);
        return pagedArticles.map(article -> new ArticleToSendDTO(article));
    }

    /**
     * Get my article, given the ID.
     */
    @GetMapping("/{articleId}")
    public ArticleToSendDTO findOwnArticleById(@AuthenticationPrincipal User currentUser, 
                                                     @PathVariable UUID articleId)
    {
        return new ArticleToSendDTO(    
                this.articlesService.findOwnArticleById(articleId, currentUser)
        );
    }


    /**
     * Update my article, given the ID.
     */
    @PutMapping("/{articleId}")
    public ArticleToSendDTO updateOwnArticleById(@AuthenticationPrincipal User currentUser,
                                                 @PathVariable UUID articleId,
                                                 @RequestBody @Validated UpdatedArticleSentDTO body)
    {
        return new ArticleToSendDTO(
                this.articlesService.updateOwnArticleById(articleId, body, currentUser)
        );
    }

    /**
     * Delete my article, given the ID.
     */
    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwnArticleById(@AuthenticationPrincipal User currentUser,
                                                 @PathVariable UUID articleId)
    {
        this.articlesService.deleteOwnArticleById(articleId, currentUser);
    }



}

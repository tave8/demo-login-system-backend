package giuseppetavella.demo_login_system.controllers;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.payloads.in_request.NewArticleSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.ArticleAfterAddedDTO;
import giuseppetavella.demo_login_system.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    
    @Autowired
    private ArticlesService articlesService;

    @PostMapping
    public ArticleAfterAddedDTO addArticle(@RequestBody @Validated NewArticleSentDTO body, 
                                           @AuthenticationPrincipal User currentUser) 
    {
        return new ArticleAfterAddedDTO(
                this.articlesService.addArticle(body, currentUser)
        );
    }

}

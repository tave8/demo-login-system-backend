package giuseppetavella.demo_login_system.payloads.in_response;

import giuseppetavella.demo_login_system.entities.Article;

import java.time.LocalDateTime;
import java.util.UUID;

public class ArticleAfterAddedDTO {
    
    private final UUID articleId;
    private final String title;
    private final String content;
    private final String coverUrl;
    private final LocalDateTime createdAt;
    
    public ArticleAfterAddedDTO(Article article) {
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.coverUrl = article.getCoverUrl();
        this.createdAt = article.getCreatedAt();
    }

    public UUID getArticleId() {
        return articleId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }
}

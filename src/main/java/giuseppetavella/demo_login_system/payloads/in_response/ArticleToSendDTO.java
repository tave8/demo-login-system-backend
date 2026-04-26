package giuseppetavella.demo_login_system.payloads.in_response;

import giuseppetavella.demo_login_system.entities.Article;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ArticleToSendDTO {
    
    private final UUID articleId;
    private final String title;
    private final String content;
    private final String coverUrl;
    private final OffsetDateTime createdAt;
    
    public ArticleToSendDTO(Article article) {
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }
}

package giuseppetavella.demo_login_system.entities;

import giuseppetavella.demo_login_system.exceptions.InvalidDataException;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "articles")
public class Article {
    
    @Id 
    @GeneratedValue
    private UUID articleId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String content;
    
    @Column(name = "cover_url", nullable = false)
    private String coverUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    protected Article() {}
    
    public Article(User user, String title, String content) {
        this.user = user;
        this.setTitle(title);
        this.setContent(content);
        this.setCoverUrl("");
        this.createdAt = LocalDateTime.now();
    }

    public UUID getArticleId() {
        return articleId;
    }
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws InvalidDataException {
        if(content == null) {
            throw new InvalidDataException("The content of an article cannot be null.");
        }
        if(content.trim().isEmpty()) {
            throw new InvalidDataException("The content of an article cannot be empty.");
        }
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InvalidDataException {
        if(title == null) {
            throw new InvalidDataException("The title of an article cannot be null.");
        }
        if(title.trim().isEmpty()) {
            throw new InvalidDataException("The title of an article cannot be empty.");
        }
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

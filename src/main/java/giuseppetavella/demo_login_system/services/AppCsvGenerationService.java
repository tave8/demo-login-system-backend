package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.helpers.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCsvGenerationService extends CsvGenerationService {
    
    @Autowired
    private ArticlesService articlesService;
    
    public byte[] generateArticlesReport() {
        
        List<Article> articles = this.articlesService.findAll();
        
        StringBuilder sb = new StringBuilder();

        sb.append("Author,Title,Content\n"); // header row
        for (Article article : articles) {
            sb.append(article.getUser().getFirstname()).append(",")
                    .append(article.getTitle()).append(",")
                    .append(article.getContent()).append("\n");
        }
        
        return sb.toString().getBytes();
        
    }
    
    public String generateArticlesReportAttachment() {
        return FileHelper.toBase64(this.generateArticlesReport());
    }
    
}

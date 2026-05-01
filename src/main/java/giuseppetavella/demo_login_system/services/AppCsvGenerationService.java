package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.helpers.FileHelper;
import giuseppetavella.demo_login_system.models.Csv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCsvGenerationService extends CsvGenerationService {
    
    @Autowired
    private ArticlesService articlesService;
    
    
    public byte[] generateArticlesReport() {
        
        List<Article> articles = this.articlesService.findAll();
        
        String[] fields = {"Author", "Title", "Content"};
        
        Csv csv = new Csv(fields);
        
        for (Article article : articles) {
            csv.addRow(
                article.getUser().getFirstname(),
                article.getTitle(),
                article.getContent()
            );
        }
        
        return csv.toBytes();
        
    }
    
    
}

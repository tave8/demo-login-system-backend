package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.models.Csv;
import giuseppetavella.demo_login_system.services.base.CsvService;
import giuseppetavella.demo_login_system.services.file_generators.CsvGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCsvService extends CsvService {
    
    @Autowired
    private ArticlesService articlesService;
    
    // this should return a Csv instance
    public Csv generateArticlesReport() {
        
        List<Article> articles = this.articlesService.findAll();
        
        String[] fields = {"Author", "Title", "Content"};

        CsvGeneratorService csv = new CsvGeneratorService(fields);
        
        for (Article article : articles) {
            csv.addRow(
                article.getUser().getFirstname(),
                article.getTitle(),
                article.getContent()
            );
        }
        
        return new Csv(csv);
        
    }
    
    
}

package giuseppetavella.demo_login_system.runners;


import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.services.ArticlesService;
import giuseppetavella.demo_login_system.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataRunner implements CommandLineRunner {

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private ArticlesService articlesService;
    
    @Override
    public void run(String... args) throws Exception {
        // System.out.println("hello");
        
        // User user1 = new User(
        //         "giuseppetavella8+@gmail.com",
        //         "1234",
        //         "Giuseppe",
        //         "Tavella"
        // );
        

        // ****** FIND BY ID
        // this.usersService.addUser(user1);
        // User user1FromDB = this.usersService.findById("b9d38a58-a36d-49a0-b353-032a9d47c9f6");

        // System.out.println(user1FromDB);
        //
        // Article article1 = new Article(
        //     user1,
        //     "article 1",
        //     "content"    
        // );
        
        // this.articlesService.addArticle(article1);
        // System.out.println(this.usersService.existsByEmail("giuseppetavella8@gmail.com"));
        
    }
}

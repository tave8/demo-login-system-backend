package giuseppetavella.demo_login_system.runners;


import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataRunner implements CommandLineRunner {

    @Autowired
    private UsersService usersService;
    
    @Override
    public void run(String... args) throws Exception {
        // System.out.println("hello");
        
        User user1 = new User(
                "giuseppetavella8@gmail.com",
                "1234",
                "Giuseppe",
                "Tavella"
        );

        System.out.println(this.usersService.existsByEmail("giuseppetavella8@gmail.com"));
        
    }
}

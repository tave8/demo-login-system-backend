package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.services.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AIDemoRunner implements CommandLineRunner {
    
    @Autowired
    private AIService aiService;
    
    @Override
    public void run(String... args) throws Exception {
        
        
        String answer = aiService.ask("Is this grammar correct? Today i liked it but the pizza was more better than before");

        System.out.println(answer);
        
    }
}

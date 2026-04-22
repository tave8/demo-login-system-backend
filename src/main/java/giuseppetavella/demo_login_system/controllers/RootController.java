package giuseppetavella.demo_login_system.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {
    
    @GetMapping
    public String root() {
        return "the server works";
    }
    
}

package giuseppetavella.demo_login_system.controllers;


// import giuseppetavella.demo_login_system.services.AuthService;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.EmailVerificationException;
import giuseppetavella.demo_login_system.exceptions.PayloadValidationException;
import giuseppetavella.demo_login_system.payloads.in_request.LoginSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.RegistrationSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.AfterLoginDTO;
import giuseppetavella.demo_login_system.payloads.in_response.AfterRegistrationDTO;
import giuseppetavella.demo_login_system.services.AuthService;
import giuseppetavella.demo_login_system.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UsersService usersService;


    /**
     * Login a user.
     */
    @PostMapping("/login")
    public AfterLoginDTO login(@RequestBody @Validated LoginSentDTO body) {
        return authService.login(body);
    }


    /**
     * Sign up a user.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AfterRegistrationDTO register(@RequestBody @Validated RegistrationSentDTO body,
                                         BindingResult validation) {

        if (validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new PayloadValidationException(errors);
        }
        
        return this.authService.signup(body);

    }


    /**
     * Verify if the code is valid.
     * If yes, the email of the account associated with this code, 
     * will be marked as verified.
     * 
     * Returns a simple html page saying that email is now verified.
     */
    @GetMapping("/verify-email/{code}")
    public String verifyEmail(@PathVariable String code) {
        
        try {
            
            this.authService.verifyEmailVerificationCode(code);
            
        } catch (EmailVerificationException ex) {
            return ex.getMessage();
        }
        
        
        return "Your email was verified. Thank you. You may close this page and login.";
    }
    

}
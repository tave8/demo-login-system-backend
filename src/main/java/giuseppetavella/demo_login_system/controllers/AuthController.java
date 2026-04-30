package giuseppetavella.demo_login_system.controllers;


// import giuseppetavella.demo_login_system.services.AuthService;
import giuseppetavella.demo_login_system.exceptions.EmailVerificationException;
import giuseppetavella.demo_login_system.exceptions.ForgotPasswordVerificationException;
import giuseppetavella.demo_login_system.exceptions.InvalidUUIDStringException;
import giuseppetavella.demo_login_system.exceptions.PayloadValidationException;
import giuseppetavella.demo_login_system.helpers.PayloadValidationHelper;
import giuseppetavella.demo_login_system.helpers.StringHelper;
import giuseppetavella.demo_login_system.payloads.in_request.LoginSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.RegistrationSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.forgot_password.ForgotPasswordRequestWithEmailSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.forgot_password.VerifyForgotPasswordCodeSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.AfterLoginDTO;
import giuseppetavella.demo_login_system.payloads.in_response.AfterRegistrationDTO;
import giuseppetavella.demo_login_system.payloads.in_response.forgot_password.ForgotPasswordToSendDTO;
import giuseppetavella.demo_login_system.services.AuthService;
import giuseppetavella.demo_login_system.services.ForgotPasswordService;
import giuseppetavella.demo_login_system.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private ForgotPasswordService forgotPasswordService;



    // ************************************
    // SIGNUP & LOGIN
    // ************************************
    
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

    
    // ************************************
    // VERIFY USER'S EMAIL
    // ************************************

    
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
    
    // ************************************
    // FORGOT PASSWORD 
    // ************************************
    
    /**
     * Verify if user with this email is allowed to set a new password. 
     * Checks if email exists, has been verified etc. 
     * and eventually generates and emails a new code.
     */
    @PostMapping("/forgot-password/request")
    public ForgotPasswordToSendDTO forgotPasswordGrantAuthorization(
                                        @RequestBody @Validated ForgotPasswordRequestWithEmailSentDTO body, 
                                        BindingResult validation) 
    {

        PayloadValidationHelper.requireNoErrors(validation);
        
        String email = body.email();
        
        this.forgotPasswordService.grantAuthorizationCodeToEmail(email);
        
        String message = "We've just sent you an email with a unique authorization link. "
                        +"For your security, the link will expire soon and can only be used once.";
        
        return new ForgotPasswordToSendDTO(message);

    }


    /**
     * The frontend sends a request here, to verify 
     * whether the user (associated with the input code) 
     * can actually set new password on the "set new password" page. 
     * Then we should mark this code as clicked or similar logic.
     */
    @PostMapping("/forgot-password/verify")
    public void forgotPasswordVerifyAuthorization(@RequestBody @Validated VerifyForgotPasswordCodeSentDTO body,
                                                  BindingResult validation)
    {

        PayloadValidationHelper.requireNoErrors(validation);
        
        try {
            
            UUID code = StringHelper.parseUUID(body.code());

        } catch(InvalidUUIDStringException ex) {
            throw new ForgotPasswordVerificationException("Code is not valid (error 10).");
        }
        
        //
        // this.forgotPasswordService.verifyAuthorizationCode(code);
        
        // this.forgotPasswordService.ifEmailIsAuthorized(email);
        //
        // String message = "We've just sent you an email with a unique authorization link. "
        //         +"For your security, the link will expire soon and can only be used once.";
        //
        // return new ForgotPasswordToSendDTO(message);

    }



}
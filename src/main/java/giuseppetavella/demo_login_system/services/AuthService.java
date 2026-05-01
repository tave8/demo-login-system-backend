package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.EmailVerificationCode;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.EmailVerificationException;
import giuseppetavella.demo_login_system.exceptions.NotFoundException;
import giuseppetavella.demo_login_system.exceptions.UnauthorizedException;
import giuseppetavella.demo_login_system.payloads.in_request.LoginSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.RegistrationSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.AfterLoginDTO;
import giuseppetavella.demo_login_system.payloads.in_response.AfterRegistrationDTO;
import giuseppetavella.demo_login_system.repositories.EmailVerificationRepository;
import giuseppetavella.demo_login_system.repositories.UsersRepository;
import giuseppetavella.demo_login_system.security.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenTools tokenTools;
    
    @Autowired
    private AppEmailService appEmailService;
    
    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private PasswordEncoder bcrypt;


    
    /**
     *  Call this only when the user is trying to login.
     *      * The user can proceed with the login, only if the account 
     *      * associated with the email that they sent, is 
     */
    public AfterLoginDTO login(LoginSentDTO body) throws NotFoundException {
        
        User userFound;
        String accessToken;

        try {

            userFound = this.usersService.findByEmail(body.email());

            // we compare the password coming from the request's body
            // with the actual password found in the database
            boolean isPasswordMatch = this.bcrypt.matches(body.password(), userFound.getPassword());

            // se la password dell'utente corrisponde a quella che si trova
            // nell'utente che ha questa email, vuol dire che l'utente si è loggato
            // con successo, quindi crea il token
            if (isPasswordMatch) {
                
                accessToken = this.tokenTools.generateToken(userFound);
                
            } else {
                throw new UnauthorizedException("Wrong credentials.");
            }

        } catch (NotFoundException ex) {
            throw new UnauthorizedException("Wrong credentials.");
        }
        
        
        // user has not verified their email
        if(!userFound.isVerifiedEmail()) {
            
            String verificationUrl = this.emailVerificationService.generateNewEmailVerificationUrl(userFound);
            this.appEmailService.sendVerifyEmail(userFound, verificationUrl);
            
            // System.out.println("USER HAS NOT VERIFIED THEIR EMAIL");
            throw new EmailVerificationException("User can login only after verifying their email. "
                                                +"An email has been sent with a new verification link.");
        }
        
        
        return new AfterLoginDTO(accessToken);
        
    }


    /**
     * Register/sign up a user.
     */
    public AfterRegistrationDTO signup(RegistrationSentDTO body) {
        
        // add a user to DB
        User newUser = this.usersService.addUser(body);

        // send email verification code to this user
        String verificationUrl = this.emailVerificationService.generateNewEmailVerificationUrl(newUser);
        
        // sends email
        this.appEmailService.sendVerifyEmail(newUser, verificationUrl);
        
        return new AfterRegistrationDTO(newUser);
    }


    
    
}
package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.ForgotPasswordCode;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.EmailVerificationException;
import giuseppetavella.demo_login_system.exceptions.ForgotPasswordVerificationException;
import giuseppetavella.demo_login_system.exceptions.NotFoundException;
import giuseppetavella.demo_login_system.repositories.ForgotPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordService {
    
    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private AppEmailService appEmailService;
    
    private final String frontendUrl;
    
    // how much time the user must wait before being authorized
    // to receive a new code (in minutes)
    private static final long NEXT_CODE_WAIT_TIME = 30;

    
    public ForgotPasswordService(
            @Qualifier("frontendUrl") String frontendUrl)
    {
        this.frontendUrl = frontendUrl;
    }



    /**
     * ## FORGOT PASSWORD: STEP 1
     * 
     * Grant the user associated with the input email,
     * the permission to set a new password.
     * Includes sending an email with verification link.
     *
     * @throws ForgotPasswordVerificationException if the user is not authorized to set a password
     */
    public void ifEmailIsAuthorized(String email) throws ForgotPasswordVerificationException 
    {
        try {
            // this email must exist and must have been verified
            User emailOwner = this.requireVerifiedEmail(email);
            
            // if this email has requested a forgot password in the last x time     
            this.requireLimitedAttempts(emailOwner);
            
            // // if all verification steps are completed, generate a new code 
            ForgotPasswordCode newCode = this.whenGenerateNewCode(emailOwner);
            //
            //
            String authorizationUrl = this.buildForgotPasswordAuthorizationUrl(newCode.getCode());
            //
            // // send an email to the user, with this code     
            this.appEmailService.sendForgotPasswordAuthorization(emailOwner, authorizationUrl);
            
        } catch(NotFoundException ex) {
            
            // user with this email was not found     
            throw new ForgotPasswordVerificationException("You cannot reset your email. (error 1)");
            
        } catch(EmailVerificationException ex) {
            
            // email was not verified      
            throw new ForgotPasswordVerificationException("You must verify your email first. (error 2)");
            
        } catch(ForgotPasswordVerificationException ex) {
            
            // user has tried too many attempts
            throw new ForgotPasswordVerificationException("You cannot reset your password right now. Try again later. (error 3)");
            
        }
        
    }

    /**
     * Generate a new code and save it in DB.
     * It also involves making all other codes of this user as unusable.
     * 
     * Ideally, this code is what you email to the user, 
     * so that they can click it.
     */
    @Transactional
    private ForgotPasswordCode whenGenerateNewCode(User user) 
    {
        
        // mark all the codes for this user as unusable 
        this.forgotPasswordRepository.markAllCodesAsUnusable(user);
        
        // this code is the only one, at this moment, that is usable
        ForgotPasswordCode newCode = new ForgotPasswordCode(user);
        
        return this.forgotPasswordRepository.save(newCode);
    }
    

    /**
     * ## FORGOT PASSWORD: STEP 2
     * 
     * Grant the user associated with the input code,
     * the permission to access the page where the user
     * will set the new password.
     */
    // public void ifCodeIsValidBeforeClick(String code) {
    //    
    // }

    /**
     * If the code is valid after click.
     */
    // public void ifCodeIsValidAfterClick(String code) {
    //    
    // }


    /**
     * ## FORGOT PASSWORD: STEP 3
     * 
     * Grant the user to finally set the new password, 
     * if the code is still valid.
     */
    // public void ifCodeCanSetPassword(String code, String newPassword) {
    //     // the user cannot set a new password, 
    //     // if the code is not valid after the click
    //     this.ifCodeIsValidAfterClick(code);
    //    
    //    
    //    
    // }


    /**
     * Get the last code of the given user, if it exists.
     */
    private Optional<ForgotPasswordCode> getLastCodeByUser(User user) 
    {
        return this.forgotPasswordRepository.getLastCodeByUser(user);
    }

    
    /**
     * Require the given user to not have tried 
     * too many times, i.e. to not have done too many attempts.
     */
    private void requireLimitedAttempts(User user) throws ForgotPasswordVerificationException  
    {
        
        Optional<ForgotPasswordCode> maybeCode = this.getLastCodeByUser(user);
        
        // if the user has no codes, it means they've never requested
        // a forgot password, which means, they've passed the verification
        if(maybeCode.isEmpty()) {
            return;
        }
        
        // the user has at least one forgot password code, 
        // and now we have their last one chronologically
        ForgotPasswordCode code = maybeCode.get();

        OffsetDateTime now = OffsetDateTime.now();
        
        // if the time of code creation + the wait time
        // is less than now, the user has passed this verification
        if (code.getCreatedAt().plusMinutes(NEXT_CODE_WAIT_TIME).isBefore(now)) {
            return;
        }
        
        
        throw new ForgotPasswordVerificationException("The user has tried too many times "
                                                        +"to reset their password. They must wait.");    
    }

    
    /**
     * 
     * Require that this email exists and the user 
     * associated to it has verified it
     * 
     * @throws NotFoundException is a user with this email does not exist
     * @throws EmailVerificationException if this email has not been verified
     */
    private User requireVerifiedEmail(String email) throws NotFoundException, 
                                                            EmailVerificationException
    {

        User emailOwner = this.usersService.findByEmail(email);

        // if this email exists but the user has not verified it
        if(!emailOwner.isVerifiedEmail()) {
            throw new EmailVerificationException("Email is not verified.");
        }
        
        return emailOwner;
        
    }



    /**
     * Build the forgot password authorization url from the verification code.
     * This will be the clickable link shown in the email.
     */
    private String buildForgotPasswordAuthorizationUrl(String code) {
        String path = "/forgot-password/verify/" + code;
        return this.frontendUrl + path;
    }

    private String buildForgotPasswordAuthorizationUrl(UUID code) {
        return this.buildForgotPasswordAuthorizationUrl(code.toString());
    }
    
}

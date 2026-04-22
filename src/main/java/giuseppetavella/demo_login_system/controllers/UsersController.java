package giuseppetavella.demo_login_system.controllers;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.payloads.in_request.UpdatedArticleSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.UpdatedProfileSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.ArticleToSendDTO;
import giuseppetavella.demo_login_system.payloads.in_response.ProfileToSendDTO;
import giuseppetavella.demo_login_system.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    private UsersService usersService;


    /**
     * Get my profile.
     */
    @GetMapping("/me")
    public ProfileToSendDTO getOwnProfile(@AuthenticationPrincipal User currentUser)
    {
        return new ProfileToSendDTO(
                this.usersService.findById(currentUser.getUserId())
        );
    }
    
    /**
     * Update my profile.
     */
    @PutMapping("/me")
    public ProfileToSendDTO updateOwnProfile(@AuthenticationPrincipal User currentUser,
                                             @RequestBody @Validated UpdatedProfileSentDTO body)
    {
        return new ProfileToSendDTO(
                this.usersService.updateOwnProfile(currentUser, body)
        );
    }
    
    
}

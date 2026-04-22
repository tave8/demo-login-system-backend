package giuseppetavella.demo_login_system.payloads.in_response;

import giuseppetavella.demo_login_system.entities.User;

import java.util.UUID;

public class AfterRegistrationDTO {
    private final UUID userId;
    
    public AfterRegistrationDTO(User user) {
        this.userId = user.getUserId();    
    }

    public UUID getUserId() {
        return userId;
    }
}

package giuseppetavella.demo_login_system.payloads.in_response.forgot_password;

import giuseppetavella.demo_login_system.entities.ForgotPasswordCode;

public class ForgotPasswordToSendDTO {
    
    private final String message;
    
    public ForgotPasswordToSendDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

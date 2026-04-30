package giuseppetavella.demo_login_system.payloads.in_request.forgot_password;

import jakarta.validation.constraints.NotNull;

public record ForgotPasswordNewPasswordSentDTO(
        
        @NotNull(message = "Missing 'code' field.")
        String code, 
        
        @NotNull(message = "Missing 'newPassword' field.")
        String newPassword
        
) {
}

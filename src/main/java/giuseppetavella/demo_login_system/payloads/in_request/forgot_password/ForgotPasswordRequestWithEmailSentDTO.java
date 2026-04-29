package giuseppetavella.demo_login_system.payloads.in_request.forgot_password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ForgotPasswordRequestWithEmailSentDTO(
        
        @NotNull(message = "Missing 'email' field.")
        @Email(message = "Email must be valid.")
        String email
        
) {
}

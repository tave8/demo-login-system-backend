package giuseppetavella.demo_login_system.payloads.in_request.forgot_password;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VerifyForgotPasswordCodeSentDTO(
        
        @NotNull(message = "Missing 'code' field.")
        String code
        
) {
}

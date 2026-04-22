package giuseppetavella.demo_login_system.payloads.in_request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginSentDTO(
        
        @NotBlank(message = "Missing 'email' field.")
        @Email(message = "Email must be valid.")
        String email,

        @NotBlank(message = "Missing 'password' field.")
        // @Size(min = 6, max = 20, message = "Password must have between 6 and 20 characters.")
        String password
) {
}

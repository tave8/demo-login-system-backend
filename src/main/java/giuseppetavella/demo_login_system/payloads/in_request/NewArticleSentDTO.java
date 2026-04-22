package giuseppetavella.demo_login_system.payloads.in_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewArticleSentDTO(
        String content,
        
        String title
) {
}

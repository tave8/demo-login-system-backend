package giuseppetavella.demo_login_system.payloads.in_request.forgot_password;

public record ForgotPasswordResetDTO(
        String code, 
        
        String newPassword
) {
}

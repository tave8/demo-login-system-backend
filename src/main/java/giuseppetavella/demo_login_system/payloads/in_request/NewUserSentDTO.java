package giuseppetavella.demo_login_system.payloads.in_request;

public record NewUserSentDTO(
        String email,
        
        String password,
        
        String firstname,
        
        String lastname
) 
{
}

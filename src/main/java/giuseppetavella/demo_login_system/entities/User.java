package giuseppetavella.demo_login_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


// this annotation allows us to never send these fields in a response
@JsonIgnoreProperties({"password", "accountNonExpired",
                        "accountNonLocked", "authorities",
                        "credentialsNonExpired", "enabled"})
public class User implements UserDetails {
    
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}

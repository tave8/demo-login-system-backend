package giuseppetavella.demo_login_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giuseppetavella.demo_login_system.enums.UserRole;
import giuseppetavella.demo_login_system.exceptions.InvalidDataException;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
// this annotation allows us to never send these fields in a response
@JsonIgnoreProperties({"password", "accountNonExpired",
                        "accountNonLocked", "authorities",
                        "credentialsNonExpired", "enabled"})
public class User implements UserDetails {
    
    @Id
    @GeneratedValue
    private UUID userId;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "avatar_url", nullable = false)
    private String avatarUrl;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    @Column(nullable = false)
    private boolean verifiedEmail;
    
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    
    protected User() {}
    
    public User(String email, String password, String firstname, String lastname, UserRole role) {
        this.email = email.toLowerCase();
        this.password = password;
        this.role = role;
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setAvatarUrl(this.getDefaultAvatarUrl());
        this.createdAt = OffsetDateTime.now();
    }

    /**
     * Default user role is USER
     */
    public User(String email, String password, String firstname, String lastname) {
        this(email, password, firstname, lastname, UserRole.USER);    
    }
    

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private String getDefaultAvatarUrl() {
        String fullname = this.getFirstname() + "+" + this.getLastname();
        return "https://ui-avatars.com/api/?name=" + fullname;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
    

    public String getEmail() {
        return email;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) throws InvalidDataException {
        if(firstname == null) {
            throw new InvalidDataException("Firstname cannot be null.");
        }
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) throws InvalidDataException {
        if(lastname == null) {
            throw new InvalidDataException("Lastname cannot be null.");
        }
        this.lastname = lastname;
    }

    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    public UserRole getRole() {
        return role;
    }

    public UUID getUserId() {
        return userId;
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(this.role.name())
        );
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

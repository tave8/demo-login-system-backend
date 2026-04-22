package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.InvalidDataFormatException;
import giuseppetavella.demo_login_system.exceptions.InvalidUUIDStringException;
import giuseppetavella.demo_login_system.exceptions.NotFoundException;
import giuseppetavella.demo_login_system.exceptions.UnauthorizedException;
import giuseppetavella.demo_login_system.payloads.in_request.NewUserSentDTO;
import giuseppetavella.demo_login_system.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    /**
     * Find a user by ID.
     */
    public User findById(UUID userId) throws NotFoundException {
        return usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId, "user"));
    }

    public User findById(String userId) throws NotFoundException {
        try {
            return this.findById(UUID.fromString(userId));
        } catch(IllegalArgumentException ex) {
            throw new InvalidUUIDStringException(userId);
        }
    }

    /**
     * A user with the given email exists?
     */
    public boolean existsByEmail(String email) {
        return this.usersRepository.existsByEmail(email);
    }

    /**
     * A user with this ID exists?
     */
    public boolean existsById(UUID userId) {
        if(userId == null) {
            return false;
        }
        return this.usersRepository.existsById(userId);
    }
    
    /**
     * Add a user.
     * Checks if the email does not exist.
     */
    public User addUser(User user) throws UnauthorizedException  {
        if(this.existsByEmail(user.getEmail())) {
            throw new UnauthorizedException("This email already exists.");
        }
        return this.usersRepository.save(user);
    }
    
    public User addUser(NewUserSentDTO body) throws UnauthorizedException {
        String hashedPassword = this.bcrypt.encode(body.password());
        
        User newUser = new User(
                body.email(),
                hashedPassword,
                body.firstname(),
                body.lastname()
        );
        
        return this.addUser(newUser);
    }


    /**
     * Update a user by ID.
     */
    // public updateById() {}
    
    
    
}

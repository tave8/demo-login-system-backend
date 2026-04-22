package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.NotFoundException;
import giuseppetavella.demo_login_system.exceptions.UnauthorizedException;
import giuseppetavella.demo_login_system.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    /**
     * Find a user by ID.
     */
    public User findById(UUID userId) throws NotFoundException {
        return usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId, "user"));
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

    /**
     * A user with the given email exists?
     */
    public boolean existsByEmail(String email) {
        return this.usersRepository.existsByEmail(email);
    }

    /**
     * Update a user by ID.
     */
    // public updateById() {}
    
    
    
}

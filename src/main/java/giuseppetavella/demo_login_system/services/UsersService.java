package giuseppetavella.demo_login_system.services;

import com.cloudinary.Cloudinary;
import giuseppetavella.demo_login_system.entities.Article;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.*;
import giuseppetavella.demo_login_system.helpers.FileHelper;
import giuseppetavella.demo_login_system.payloads.in_request.RegistrationSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.UpdatedArticleSentDTO;
import giuseppetavella.demo_login_system.payloads.in_request.UpdatedProfileSentDTO;
import giuseppetavella.demo_login_system.payloads.in_response.ProfileToSendDTO;
import giuseppetavella.demo_login_system.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private Cloudinary cloudinaryUploader;

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
     * Find a user by email.
     */
    public User findByEmail(String email) throws NotFoundException {
        User userFound = this.usersRepository.findByEmail(email);
        if (userFound == null) {
            throw new NotFoundException("User with email " + email + " was not found.");
        }
        return userFound;
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
    
    public User addUser(RegistrationSentDTO body) throws UnauthorizedException {
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
     * Update my profile, given the ID.
     */

    public User updateOwnProfile(User profile, 
                                 UpdatedProfileSentDTO profileBody)
    {
        profile.setFirstname(profileBody.firstname());
        profile.setLastname(profileBody.lastname());
        return this.usersRepository.save(profile);
    }

    
    
    /**
     * Upload my new avatar image.
     */
    public User uploadMyAvatarImage(User user, MultipartFile avatarImage) {
        // if the file is empty
        if(avatarImage.isEmpty()) {
            throw new InvalidFileUploadedException("The file uploaded cannot be empty.");
        }
        
        // if file is not an image
        if(!FileHelper.isImage(avatarImage)) {
            throw new InvalidFileUploadedException("The file uploaded is not an image.");
        }
        
        // if file is too big
        if(!FileHelper.isWithinAvatarSize(avatarImage)) {
            throw new InvalidFileUploadedException("The file uploaded ("
                                                        +FileHelper.getFileSizeInMB(avatarImage)
                                                        +"MB) is too big. Max file size is 2MB.");
        }
        
        String avatarUrl;

        try {
            // upload image to cloudinary
            Map result = cloudinaryUploader
                    .uploader()
                    // get the bytes of the file. 
                    // this is what we're going to upload to cloudinary
                    .upload(avatarImage.getBytes(), Map.of());

            avatarUrl = (String) result.get("secure_url");

        } catch (IOException | RuntimeException ex) {
            throw new FileUploadException(ex.getMessage());
        }

        // get image url, if success

        // System.out.println(avatarUrl);

        // update author (with setter)
        user.setAvatarUrl(avatarUrl);

        // save user      
        return this.usersRepository.save(user);

    }
    
}

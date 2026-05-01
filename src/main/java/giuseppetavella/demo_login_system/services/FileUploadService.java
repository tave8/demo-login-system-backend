package giuseppetavella.demo_login_system.services;

import com.cloudinary.Cloudinary;
import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.FileUploadException;
import giuseppetavella.demo_login_system.exceptions.InvalidFileUploadedException;
import giuseppetavella.demo_login_system.helpers.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileUploadService {


    @Autowired
    private Cloudinary cloudinaryUploader;


    /**
     * Upload a file.
     *
     * @return the URL of the uploaded file
     */
    public String uploadFile(MultipartFile inputFile) throws InvalidFileUploadedException, 
                                                             FileUploadException 
    {

        if (inputFile.isEmpty()) {
            throw new InvalidFileUploadedException("The inputFile uploaded cannot be empty.");
        }

        try {
            return this.uploadBytes(inputFile.getBytes());
        } catch (IOException ex) {
            throw new FileUploadException(ex.getMessage());
        }

    }

    /**
     * Upload a byte array.
     *
     * @return the URL of the uploaded file
     */
    public String uploadFile(byte[] bytes) throws InvalidFileUploadedException, 
                                                   FileUploadException 
    {

        if (bytes.length == 0) {
            throw new InvalidFileUploadedException("The byte array uploaded cannot be empty.");
        }

        return this.uploadBytes(bytes);

    }

    /**
     * Core upload logic.
     */
    private String uploadBytes(byte[] bytes) throws FileUploadException
    {
        try {
            Map result = cloudinaryUploader
                    .uploader()
                    .upload(bytes, Map.of());

            return (String) result.get("secure_url");

        } catch (IOException | RuntimeException ex) {
            throw new FileUploadException(ex.getMessage());
        }
    }
    
    
    // public uploadImage() {
    //    
    // }


}

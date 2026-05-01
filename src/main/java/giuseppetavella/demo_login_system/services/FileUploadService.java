package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.interfaces.FileUploader;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService implements FileUploader {


    @Override
    public String upload(byte[] bytes) {
        return "";
    }
}

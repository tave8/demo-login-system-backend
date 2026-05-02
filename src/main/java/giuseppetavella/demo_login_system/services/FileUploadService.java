package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.interfaces.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.net.URI;
import java.time.Duration;


@Service
public class FileUploadService {

    @Autowired
    private S3Client s3Client;
    
    // this is a dependency
    private final String bucket;
    
    // we use constructor dependency injection
    public FileUploadService(@Value("${cloudflare.r2.bucket-name}") String bucket) 
    {
        this.bucket = bucket;
    }

    public void upload(String key, byte[] data) {
        s3Client.putObject(
                PutObjectRequest.builder().bucket(this.bucket).key(key).build(),
                RequestBody.fromBytes(data)
        );
    }
    
    
}

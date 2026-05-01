package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.interfaces.FileUploader;
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

    static final String ACCOUNT_ID = "31b09b9df337d416d99d191b2802a1eb";
    static final String ACCESS_KEY_ID = "c7c6a0df87c5859ecd7bc1700ed9d3b3";
    static final String SECRET_ACCESS_KEY = "d382c35ebab008d62356e74401089b64c749086ce360f176c4ec80d4998dccab";
    static final String BUCKET = "demo-login-system";
    static final String ENDPOINT = "https://" + ACCOUNT_ID + ".r2.cloudflarestorage.com";

    public S3Client s3() {
        return S3Client.builder()
                .endpointOverride(URI.create(ENDPOINT))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ACCESS_KEY_ID, SECRET_ACCESS_KEY)))
                .region(Region.of("auto"))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .chunkedEncodingEnabled(false)
                        .build())
                .build();
    }

    public void upload(String key, byte[] data) {
        s3().putObject(
                PutObjectRequest.builder().bucket(BUCKET).key(key).build(),
                RequestBody.fromBytes(data)
        );
    }

    // Get a temporary URL (valid for 1 hour)
    // public static String getUrl(String key) {
    //     try (S3Presigner presigner = S3Presigner.builder()
    //             .endpointOverride(URI.create(ENDPOINT))
    //             .credentialsProvider(StaticCredentialsProvider.create(
    //                     AwsBasicCredentials.create(ACCESS_KEY_ID, SECRET_ACCESS_KEY)))
    //             .region(Region.of("auto"))
    //             .serviceConfiguration(S3Configuration.builder()
    //                     .pathStyleAccessEnabled(true).build())
    //             .build()) {
    //
    //         return presigner.presignGetObject(
    //                 GetObjectPresignRequest.builder()
    //                         .signatureDuration(Duration.ofHours(1))
    //                         .getObjectRequest(GetObjectRequest.builder()
    //                                 .bucket(BUCKET).key(key).build())
    //                         .build()
    //         ).url().toString();
    //     }
    // }
    
}

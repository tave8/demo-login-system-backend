package giuseppetavella.demo_login_system.helpers;

import giuseppetavella.demo_login_system.exceptions.FileDownloadException;
import giuseppetavella.demo_login_system.exceptions.UnknownFileTypeException;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

public class FileHelper {

    private static final Map<String, String> MIME_TO_EXTENSION = Map.of(
            "application/pdf",  "pdf",
            "text/csv",         "csv",
            "image/png",        "png",
            "image/jpeg",       "jpg"
    );

    // dependency to extract file extension from bytes
    private static final Tika TIKA = new Tika();

    // 1 MB
    public static final long MB = 1024 * 1024;
    
    /**
     * Is the uploaded file an image?
     */
    public static boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        if(contentType == null) {
            return false;
        }
        // the file is an image if its content type 
        // starts with image/
        return contentType.startsWith("image/");
    }

    /**
     * Get the file size in MB.
     */
    public static double getFileSizeInMB(MultipartFile file) {
        return file.getSize() / (double) FileHelper.MB;
    }

    /**
     * Is the file size smaller than the
     * provided size in bytes?
     */
    public static boolean sizeIsSmallerThan(MultipartFile file, long maxSizeInBytes) {
        return file.getSize() < maxSizeInBytes;
    }

    /**
     * Is the given file within the avatar image
     * default limit (2 MB)?
     */
    public static boolean isWithinAvatarSize(MultipartFile file) {
        return FileHelper.sizeIsSmallerThan(file, 2 * FileHelper.MB);
    }

    /**
     * byte array -> base64 
     */
    public static String toBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * URL -> base64
     */
    public static String urlToBase64(String url) throws FileDownloadException 
    {
        try {
            
            byte[] bytes = new URL(url).openStream().readAllBytes();
            return FileHelper.toBase64(bytes);
        
        } catch(MalformedURLException ex) {
            throw new FileDownloadException("URL is malformed. DETAILS: " + ex.getMessage());  
        } catch(IOException ex) {
            throw new FileDownloadException(ex.getMessage());
        }
        
    }

    /**
     * Get the file extension from bytes.
     */
    public static String getExtensionFromBytes(byte[] bytes) throws UnknownFileTypeException
    {
        String mimeType = TIKA.detect(bytes);

        if (!MIME_TO_EXTENSION.containsKey(mimeType)) {
            throw new UnknownFileTypeException(mimeType);
        }

        return MIME_TO_EXTENSION.get(mimeType);
    }
    
}

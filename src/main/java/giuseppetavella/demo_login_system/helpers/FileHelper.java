package giuseppetavella.demo_login_system.helpers;

import org.springframework.web.multipart.MultipartFile;

public class FileHelper {

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
    
}

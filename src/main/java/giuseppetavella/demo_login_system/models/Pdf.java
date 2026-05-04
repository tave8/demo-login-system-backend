package giuseppetavella.demo_login_system.models;

import giuseppetavella.demo_login_system.helpers.FileHelper;

public class Pdf {
    
    private final byte[] bytes;
    
    public Pdf(byte[] bytes) {
        this.bytes = bytes;
    }
    
    public String toAttachment() {
        return FileHelper.toBase64(this.getBytes());
    }

    public byte[] getBytes() {
        return bytes;
    }
}

package giuseppetavella.demo_login_system.models;

import giuseppetavella.demo_login_system.helpers.FileHelper;
import giuseppetavella.demo_login_system.interfaces.EmailAttachable;

public class Pdf implements EmailAttachable {
    
    private final byte[] bytes;
    
    public Pdf(byte[] bytes) {
        this.bytes = bytes;
    }
    
    @Override
    public String toAttachment() {
        return FileHelper.toBase64(this.getBytes());
    }

    public byte[] getBytes() {
        return bytes;
    }
}

package giuseppetavella.demo_login_system.enums.internal;

public enum BrowserContentDispositionHeader {
    ATTACHMENT, INLINE;

    public String getValue() {
        return this.name().toLowerCase();
    }
}

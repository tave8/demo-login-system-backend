package giuseppetavella.demo_login_system.enums.internal;

public enum CsvSeparator {
    COMMA(','),
    SEMICOLON(';');

    private final char value;

    CsvSeparator(char value) {
        this.value = value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}

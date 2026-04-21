package giuseppetavella.demo_login_system.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("L'elemento con ID " + id + " non è stato trovato.");
    }

    public NotFoundException(UUID id, String informalEntity) {
        super("L'elemento '" + informalEntity + "' con ID " + id + " non è stato trovato.");
    }

    public NotFoundException(String msg) {
        super(msg);
    }

}
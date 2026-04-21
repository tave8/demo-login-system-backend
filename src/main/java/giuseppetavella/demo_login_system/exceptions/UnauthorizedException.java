package giuseppetavella.demo_login_system.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super("Non sei autenticato o non hai l'autorizzazione per "
                +"accedere a questa risorsa. DETTAGLI: " + message);
    }
}
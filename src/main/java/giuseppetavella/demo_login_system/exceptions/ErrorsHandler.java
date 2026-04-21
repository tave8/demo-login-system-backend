package giuseppetavella.demo_login_system.exceptions;

import giuseppetavella.D5.payloads.in_response.ErroriDaMandareDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(NonTrovatoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroriDaMandareDTO handleNotFoundException(NonTrovatoException ex) {
        return new ErroriDaMandareDTO(ex.getMessage());
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErroriDaMandareDTO handleUnauthorized(UnauthorizedException ex) {
        // System.out.println("THROWN EXCEPTION* *******************");
        return new ErroriDaMandareDTO(ex.getMessage());
    }


    /**
     * Handles exceptions raised when current user of request
     * is not authorized to access an endpoint. We protect
     * the endpoint by using @PreAuthorize annotation
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroriDaMandareDTO handleAuthorizationDenied(AuthorizationDeniedException ex) {
        return new ErroriDaMandareDTO("Non hai la giusta autorizzazzione. DETTAGLI: "+ ex.getMessage());
    }

    @ExceptionHandler(ValidazionePayloadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handlePayloadValidationError(ValidazionePayloadException ex) {
        return new ErroriDaMandareDTO(ex.getMessage(), ex.getErrors());
    }


    @ExceptionHandler(CaricamentoFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleCaricamentoFile(CaricamentoFileException ex) {
        String msg = "Errore durante il caricamento di un file. DETTAGLI: " + ex.getMessage();
        return new ErroriDaMandareDTO(msg);
    }


    @ExceptionHandler(PrenotazioneNonDisponibileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handlePrenotazioneNonDisponibile(PrenotazioneNonDisponibileException ex) {
        String msg = "Prenotazione non disponibile. DETTAGLI: " + ex.getMessage();
        return new ErroriDaMandareDTO(msg);
    }

    @ExceptionHandler(NuovoStatoPrenotazioneNonValidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleNuovoStatoPrenotazioneNonValido(NuovoStatoPrenotazioneNonValidoException ex) {
        return new ErroriDaMandareDTO(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleMethodArgumentoTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = "Il tipo di qualche campo non può essere convertito nel tipo corretto. DETTAGLi: " + ex.getMessage();
        return new ErroriDaMandareDTO(msg);
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        String msg = "Questo media type non è supportato. DETTAGLI: " + ex.getMessage();
        return new ErroriDaMandareDTO(msg);
    }




    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleHTTPMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String msg = "Questo metodo HTTP non è supportato. DETTAGLI: " + ex.getMessage();
        return new ErroriDaMandareDTO(msg);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroriDaMandareDTO handleMissingRoute(NoResourceFoundException ex) {
        String msg = "Questa risorsa sembra non esistere, o non esiste questo endpoint.";
        return new ErroriDaMandareDTO(msg);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleMaybeMissingBody(HttpMessageNotReadableException ex) {
        String msg = "La richiesta non è ben formata; forse manca il body, "
                +"i campi del body non sono ben formati, o qualche valore categorico non viene soddisfatto (ENUM)?";
        return new ErroriDaMandareDTO(msg);
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleRequestIsNotMultipartRequest(MultipartException ex) {
        String msg = "L'endpoint si aspetta che questa richiesta sia multipart formdata, "
                +"ma sembra che non lo sia. Puoi provare ad impostare gli header "
                +"della richiesta con content type multipart formdata?";
        return new ErroriDaMandareDTO(msg);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriDaMandareDTO handleRequestIsMissingPart(MissingServletRequestPartException ex) {
        String msg = "L'endpoint si aspetta che questa richiesta abbia qualche parte nel multipart, "
                +"ma sembra che non ci sia. Questo può capitare se stai cercando "
                +"di caricare un file, o l'endpoint si aspetta che carichi un file? "
                +"DETTAGLI: " + ex.getMessage();
        return new ErroriDaMandareDTO(msg);
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroriDaMandareDTO handleGenericException(Exception ex) {
        ex.printStackTrace();
        return new ErroriDaMandareDTO("C'è stato un error nei server. Stiamo risolvendo.");
    }

}
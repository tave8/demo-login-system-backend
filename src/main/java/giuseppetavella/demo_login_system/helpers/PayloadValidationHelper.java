package giuseppetavella.demo_login_system.helpers;

import giuseppetavella.demo_login_system.exceptions.PayloadValidationException;
import org.springframework.validation.BindingResult;

import java.util.List;

public class PayloadValidationHelper {
    
    /**
     * Helper when validating payloads.
     * Avoids having to check for errors manually, in each controller.
     * 
     * @throws PayloadValidationException if there's at least one error in the payload validation
     */
    public static void requireNoErrors(BindingResult validation) throws PayloadValidationException 
    {
        if (validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new PayloadValidationException(errors);
        }
    }

}

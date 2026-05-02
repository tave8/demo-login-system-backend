package giuseppetavella.demo_login_system.helpers;

import giuseppetavella.demo_login_system.exceptions.PayloadValidationException;
import giuseppetavella.demo_login_system.exceptions.UnknownFileTypeException;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

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


    /**
     * Require that the type/extension of the given file 
     * conforms
     * 
     * @param expectedFileExtWithoutDot the file extension without a dot, for example "pdf" or "png"
     *                                  
     * @throws UnknownFileTypeException if the file type is not internally mapped or recognized
     * @throws PayloadValidationException if the expected file type does not match the actual file type
     */
    public static void requireFileType(MultipartFile file, 
                                       String expectedFileExtWithoutDot) throws PayloadValidationException, 
                                                                                UnknownFileTypeException
    {
        String actualFileType = FileHelper.getFileType(file);
                
        boolean hasSameType = actualFileType.equals(expectedFileExtWithoutDot.trim().toLowerCase());
        
        if(hasSameType) {
            return;    
        }
        
        throw new PayloadValidationException("The file with original name '" + file.getOriginalFilename() + "' " 
                                            + "does not match the required file type. Expected file type: '" 
                                            + expectedFileExtWithoutDot + "'. Got '" + actualFileType + "' instead.");
    }

    /**
     * Require that this file is a pdf.
     * 
     * @param file
     * @throws PayloadValidationException
     */
    public static void requiredPdf(MultipartFile file) throws PayloadValidationException, 
                                                              UnknownFileTypeException
    {
        PayloadValidationHelper.requireFileType(file, "pdf");
    }

}

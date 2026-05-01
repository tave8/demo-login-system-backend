package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.exceptions.FileUploadException;
import giuseppetavella.demo_login_system.exceptions.InvalidFileUploadedException;
import giuseppetavella.demo_login_system.exceptions.PdfGenerationException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppPdfGenerationService extends PdfGenerationService {

    /**
     * Upload an invoice.
     */
    public String uploadInvoice(Map<String, Object> vars) throws PdfGenerationException, 
                                                                 InvalidFileUploadedException,
                                                                 FileUploadException
    {
        
        return this.pdfToUpload("business/invoice", vars);
        
    }

    /**
     * Save an invoice locally.
     */
    public void saveInvoiceLocal(Map<String, Object> vars, String filename) throws PdfGenerationException
    {

        this.pdfToSaveLocal("business/invoice", vars, "/output", filename);

    }
    

    /**
     * Generate the base64 of a template.
     */
    public String generateInvoiceAttachment(Map<String, Object> vars) throws PdfGenerationException
    {
        
        return this.pdfToBase64("business/invoice", vars);
        
    }

}

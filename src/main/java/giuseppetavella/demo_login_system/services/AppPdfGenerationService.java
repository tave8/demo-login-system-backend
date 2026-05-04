package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.exceptions.FileUploadException;
import giuseppetavella.demo_login_system.exceptions.InvalidFileUploadedException;
import giuseppetavella.demo_login_system.exceptions.PdfGenerationException;
import giuseppetavella.demo_login_system.models.Pdf;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppPdfGenerationService extends PdfGenerationService {

    /**
     * Upload an invoice.
     */
    // public String uploadInvoice(Map<String, Object> vars) throws PdfGenerationException, 
    //                                                              InvalidFileUploadedException,
    //                                                              FileUploadException
    // {
    //    
    //     return this.pdfToUpload("business/invoice", vars);
    //    
    // }

    /**
     * Save an invoice locally.
     */
    // public void saveInvoiceLocal(Map<String, Object> vars, String filename) throws PdfGenerationException
    // {
    //
    //     this.pdfToSaveLocal("business/invoice", vars, "/output", filename);
    //
    // }
    

    public Pdf generateInvoice(Map<String, Object> vars) throws PdfGenerationException
    {

        return new Pdf(
                this.pdfToBytes("business/invoice", vars)
        );

    }

}

package giuseppetavella.demo_login_system.controllers;

import giuseppetavella.demo_login_system.enums.internal.BrowserContentDispositionHeader;
import giuseppetavella.demo_login_system.services.AppPdfGenerationService;
import giuseppetavella.demo_login_system.services.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/pdf-generation")
public class PdfGenerationController {

    @Autowired
    private AppPdfGenerationService appPdfGenerationService;
    
    @PostMapping("/upload-invoice")
    public String uploadInvoice() {

        Map<String, Object> vars = Map.of(
                "firstname", "Giuseppe"
        );
                
        String fileUrl = this.appPdfGenerationService.uploadInvoice(vars);
        
        return  fileUrl;
    }

    /**
     * 
     */
    @PostMapping("/save-invoice-local")
    public String saveInvoiceLocal(@RequestParam(value = "filename",defaultValue = "invoice.pdf") String filename) {

        Map<String, Object> vars = Map.of(
                "firstname", "Giuseppe"
        );

        this.appPdfGenerationService.saveInvoiceLocal(vars, filename);

        return "Invoice saved locally";
    }



}

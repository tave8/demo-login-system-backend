package giuseppetavella.demo_login_system.controllers;

import giuseppetavella.demo_login_system.enums.internal.BrowserContentDispositionHeader;
import giuseppetavella.demo_login_system.services.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/generate-pdf")
public class PdfGenerationController {

    @Autowired
    private PdfGenerationService pdfGenerationService;
    
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadInvoice() throws Exception {

        Map<String, String> vars = Map.of(
                "firstname", "Giuseppe"
        );
                
        return this.pdfGenerationService.templateToHttpResponse(
                "business/invoice",
                vars,
                "invoice.pdf",
                BrowserContentDispositionHeader.ATTACHMENT
                
        );
    }
    
}

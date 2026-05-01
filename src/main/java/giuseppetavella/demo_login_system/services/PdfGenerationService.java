package giuseppetavella.demo_login_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@Service
public class PdfGenerationService {


    @Autowired
    private TemplateEngine templateEngine;
    
    public void generateInvoice() throws IOException {

        // 1. Render Thymeleaf template to HTML string
        Context ctx = new Context();
        ctx.setVariable("firstname", "Mario Rossi");
        String html = this.templateEngine.process("business/invoice", ctx);

        // 2. Convert HTML to PDF
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html, null);
        renderer.layout();

        // 3. Save to file
        String path = System.getProperty("user.dir") + "/output/invoice.pdf";
        new File(System.getProperty("user.dir") + "/output").mkdirs();

        try (OutputStream os = new FileOutputStream(path)) {
            renderer.createPDF(os);
        }
        
        //
        // // 3. Stream it to the response
        // response.setContentType("application/pdf");
        // renderer.createPDF(response.getOutputStream());
        
        
    }
    
}

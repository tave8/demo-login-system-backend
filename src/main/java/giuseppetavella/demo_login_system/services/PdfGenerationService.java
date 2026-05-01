package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.enums.internal.BrowserContentDispositionHeader;
import giuseppetavella.demo_login_system.exceptions.FileUploadException;
import giuseppetavella.demo_login_system.exceptions.InvalidFileUploadedException;
import giuseppetavella.demo_login_system.exceptions.PdfGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateEngineException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

@Service
public class PdfGenerationService {


    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private FileUploadService fileUploadService;
    
    
    /**
     * template + vars -> HTTP response entity 
     */
    public ResponseEntity<byte[]> templateToHttpResponse(String template,
                                                        Map<String, Object> vars,
                                                        String outputFilename,
                                                        BrowserContentDispositionHeader contentDispositionHeader) 
    {
        // template -> html
        String html = this.templateToHtml(template, vars);
        // html -> pdf
        ByteArrayOutputStream baos = this.htmlToPdf(html);
        // pdf -> http response
        return this.pdfToHttpResponse(baos, outputFilename, contentDispositionHeader);
    }

    
    /**
     * template + vars -> PDF
     */
    public ByteArrayOutputStream templateToPdf(String template,
                                               Map<String, Object> vars) throws PdfGenerationException
    {
        // template -> html 
        String html = this.templateToHtml(template, vars);
        // html -> pdf 
        return this.htmlToPdf(html);
    }
    

    
    /**
     * template + vars -> HTML
     */
    public String templateToHtml(String template, Map<String, Object> vars) throws PdfGenerationException 
    {
        
        Context ctx = new Context();
        
        // fill the template with the given vars
        for(String var : vars.keySet()) {
            ctx.setVariable(var, vars.get(var));
        }

        try {
            
            return this.templateEngine.process(template, ctx);
            
        } catch (TemplateEngineException ex) {
            throw new PdfGenerationException("Failed to render template: " + template + " DETAILS: " + ex.getMessage());
        }

    }
    

    /**
     * HTML -> PDF
     */
    public ByteArrayOutputStream htmlToPdf(String html) throws PdfGenerationException 
    {
        // Generate PDF into memory
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        
        try {
            
            renderer.setDocumentFromString(html, null);
            renderer.layout();
            renderer.createPDF(baos);
            return baos;
            
        } catch (Exception ex) {
            
            throw new PdfGenerationException("Failed to generate PDF from HTML. DETAILS: " + ex.getMessage());
       
        }
        
    }

    

    /**
     * PDF -> HTTP response entity
     */
    public ResponseEntity<byte[]> pdfToHttpResponse(ByteArrayOutputStream baos, 
                                                         String outputFilename,
                                                         BrowserContentDispositionHeader contentDispositionHeader) 
    {

        byte[] pdf = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(contentDispositionHeader.getValue(), outputFilename);

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


    /**
     * 
     * PDF -> upload
     * 
     * @return URL of uploaded file
     */
    public String pdfToUpload(String template, Map<String, Object> vars) throws PdfGenerationException, 
                                                                                InvalidFileUploadedException, 
                                                                                FileUploadException
    {
        ByteArrayOutputStream pdf = this.templateToPdf(template, vars);
        byte[] byteArray = pdf.toByteArray();
        return this.fileUploadService.uploadFile(byteArray);
    }


    /**
     * PDF -> save local
     */
    public void pdfToSaveLocal(String template, 
                               Map<String, Object> vars, 
                               String outputDir, 
                               String outputFilename) throws PdfGenerationException 
    {

        try {
            
            String html = this.templateToHtml(template, vars);

            // Convert HTML to PDF
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html, null);
            renderer.layout();

            // Save to file
            String path = System.getProperty("user.dir") + outputDir + "/" + outputFilename;
            new File(System.getProperty("user.dir") + outputDir).mkdirs();

            try (OutputStream os = new FileOutputStream(path)) {
                renderer.createPDF(os);
            }
            
        } catch(IOException ex) {
            
            throw new PdfGenerationException(ex.getMessage());
            
        }
        

    }
    
}

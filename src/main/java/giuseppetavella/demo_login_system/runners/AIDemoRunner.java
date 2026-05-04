package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.services.AppAIService;
import giuseppetavella.demo_login_system.services.AppPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class AIDemoRunner implements CommandLineRunner {
    
    @Autowired
    private AppAIService appAIService;
    
    @Autowired
    private AppPdfService appPdfGenerationService;
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public void run(String... args) throws Exception {

        // byte[] myCvBytes = FileHelper.readPdf("extra/my_cv.pdf");
        
        
        // String answer = aiService.ask("Is this grammar correct? Today i liked it but the pizza was more better than before");

        // byte[] pdfBytes = appPdfGenerationService.generateInvoice(Map.of());

        // String jsonStr = aiService.askWithPdf(myCvBytes,  """
        //              Extract the following fields from this CV and return ONLY a JSON object,
        //                 no markdown, no backticks, no preamble. If a field is not found, set it to null.
        //                 For arrays, return an empty array if nothing is found.
        //         {
        //             "full_name": null,
        //             "date_of_birth": null,
        //             "email": null,
        //             "phone": null,
        //             "address": null,
        //             "nationality": null,
        //             "education": [
        //                 {
        //                     "degree": null,
        //                     "institution": null,
        //                     "year": null
        //                 }
        //             ],
        //             "experience": [
        //                 {
        //                     "company": null,
        //                     "role": null,
        //                     "from": null,
        //                     "to": null,
        //                     "description": null
        //                 }
        //             ],
        //             "skills": [],
        //             "languages": [
        //                 {
        //                     "language": null,
        //                     "level": null
        //                 }
        //             ],
        //             "certifications": []
        //         }
        //             Return ONLY the JSON object, no markdown, no backticks, no preamble.
        //         """);

        // 2. JSON string → Java object (to parse the response)
        //
        // CvData cvData = mapper.readValue(jsonStr, CvData.class);   // "{...}" → WorkerData
        //
        // System.out.println(cvData.getEmail());
        // System.out.println(cvData.getLanguages());
        // System.out.println(cvData.getFullName());
        // System.out.println(cvData.getDateOfBirth());
        // System.out.println(cvData.getPhone());
        // System.out.println(cvData.getFullName());
        
        // System.out.println(worker.address());
        // System.out.println(worker.full_name());
        
        // System.out.println(jsonStr);
        
        
    }
}

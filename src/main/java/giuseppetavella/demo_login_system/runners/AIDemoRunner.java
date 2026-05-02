package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.WorkerData;
import giuseppetavella.demo_login_system.services.AIService;
import giuseppetavella.demo_login_system.services.AppPdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Component
public class AIDemoRunner implements CommandLineRunner {
    
    @Autowired
    private AIService aiService;
    
    @Autowired
    private AppPdfGenerationService appPdfGenerationService;
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public void run(String... args) throws Exception {

        
        // String answer = aiService.ask("Is this grammar correct? Today i liked it but the pizza was more better than before");

        byte[] pdfBytes = appPdfGenerationService.generateInvoice(Map.of());
        
        String jsonStr = aiService.askWithPdf(pdfBytes,  """
                    Extract the following fields from this document:
                    {
                        "full_name": "",
                        "date_of_birth": "",
                        "email": "",
                        "phone": "",
                        "address": ""
                    }
                    Return ONLY the JSON object, no markdown, no backticks, no preamble.
                """);

        // 2. JSON string → Java object (to parse the response)
        
        WorkerData worker = mapper.readValue(jsonStr, WorkerData.class);   // "{...}" → WorkerData

        System.out.println(worker.address());
        System.out.println(worker.full_name());
        
        // System.out.println(jsonStr);
        
        
    }
}

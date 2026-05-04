package giuseppetavella.demo_login_system.services.file_generators;

import giuseppetavella.demo_login_system.exceptions.HtmlTemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateInputException;

import java.util.Map;

/**
 * Fill HTML templates.
 */
@Component
public class HtmlTemplateService {
    
    @Autowired
    private TemplateEngine templateEngine;

    
    /**
     * Fill a HTML template located in directory "resources".
     * 
     * @throws HtmlTemplateException if input template is not valid / does not exist
     */
    public String fillTemplate(String template, Map<String, Object> vars) 
    {

        Context context = new Context();

        // populate the template with the given vars
        for(String var : vars.keySet()) {
            context.setVariable(var, vars.get(var));
        }

        try {

            return this.templateEngine.process(template, context);

        } catch(TemplateInputException ex) {

            throw new HtmlTemplateException("This template does not seem to exist. DETAILS: " + ex.getMessage());

        }

    }
    
}

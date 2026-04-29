package giuseppetavella.demo_login_system.config;

import com.cloudinary.Cloudinary;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("application.properties")
public class Config {

    /**
     * The URL of this server.
     */
    @Bean(name = "serverUrl")
    public String getServerUrl(@Value("${server.url}") String serverUrl) {
        return serverUrl;
    }

    /**
     * The URL of that this server is "connected" to.
     * You can see this as the frontend URL that belongs 
     * to the same environment scope as the server:
     * 
     * local server -> local frontend
     * preview server -> preview frontend
     * production server -> production frontend
     */
    @Bean(name = "frontendUrl")
    public String getFrontendUrl(@Value("${whereami}") String whereami, 
                                 @Value("${frontend.production.url}") String frontendProductionUrl,
                                 @Value("${frontend.preview.url}") String frontendPreviewUrl,
                                 @Value("${frontend.local.url}") String frontendLocalUrl) 
    {
        if(whereami.equals("LOCAL")) {
            return frontendLocalUrl;
        }
        if(whereami.equals("PREVIEW")) {
            return frontendPreviewUrl;
        }
        if(whereami.equals("PRODUCTION")) {
            return frontendProductionUrl;
        }
        throw new RuntimeException("While loading env vars in a "
                                    +"Spring bean, the value '" + whereami+ "' for 'whereami' was not "
                                    +"found in the env vars related to frontend URL." );
    }


    @Bean
    public Cloudinary getFileUploader(
            @Value("${cloudinary.name}") String cloudName,
            @Value("${cloudinary.apikey}") String apiKey,
            @Value("${cloudinary.secret}") String apiSecret)
    {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }

    /**
     * The instance will be using to send emails.
     */
    @Bean 
    public Resend getEmailSender(
                @Value("${resend.apikey}") String resendApiKey) 
    {
        return new Resend(resendApiKey);    
    }

    /**
     * Default params for sending an email.
     * For example, a default domain, user and name.
     */
    @Bean
    public CreateEmailOptions.Builder getEmailSenderOptions(
            @Value("${email.sender-domain}") String domain,
            @Value("${email.sender-user}") String user,
            @Value("${email.sender-name}") String name)
    {
        // something like "info@mydomain.com"
        String senderAddress = user + "@" + domain;
        // something like "Giuseppe Tavella <info@mydomain.com>"
        String nameAndSenderAddress = name + " <" + senderAddress + ">";
        
        return CreateEmailOptions.builder().from(nameAndSenderAddress);
    }


}
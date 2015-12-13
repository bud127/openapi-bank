package openapi.bank.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author chanwook
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ClientApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClientApp.class);
    }
}

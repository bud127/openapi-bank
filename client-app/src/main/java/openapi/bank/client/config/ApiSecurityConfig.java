package openapi.bank.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

/**
 * @author chanwook
 */
@Configuration
@EnableOAuth2Client
public class ApiSecurityConfig {

    @Value("${api.accessTokenURI}")
    String accessTokenURI;

    @Value("${api.userAuthorizationURI}")
    String userAuthorizationURI;

    @Bean
    public OAuth2ProtectedResourceDetails accountResourceDetail() {
        final AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        resourceDetails.setId("account");
        resourceDetails.setClientId("client-app");
        resourceDetails.setClientSecret("_client_app_secret");
        resourceDetails.setAccessTokenUri(accessTokenURI);
        resourceDetails.setUserAuthorizationUri(userAuthorizationURI);
        resourceDetails.setScope(Arrays.asList("ACCOUNT_INQUIRY", "ACCOUNT_TRANSFER"));

        return resourceDetails;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(accountResourceDetail(), clientContext);
    }
}

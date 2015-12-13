package openapi.bank.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author chanwook
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
            .requestMatchers()
                .antMatchers("/account/", "/oauth/users/**", "/oauth/clients/**", "/profile")
                .and()
            .authorizeRequests()
                .antMatchers("/account/inquiry").access("#oauth2.hasScope('ACCOUNT_INQUIRY') and hasRole('ROLE_USER')")
                .antMatchers("/account/transfer").access("#oauth2.hasScope('ACCOUNT_TRANSFER') and hasRole('ROLE_USER')")
                ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("account").stateless(false);
    }
}

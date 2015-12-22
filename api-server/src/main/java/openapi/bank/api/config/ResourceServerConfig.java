package openapi.bank.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author chanwook
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String IS_NOT_OAUTH_USER = " (!#oauth2.isOAuth() and hasRole('ROLE_USER')) ";

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().permitAll()
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
            .requestMatchers()
                .antMatchers("/account/", "/oauth/users/**", "/oauth/clients/**", "/profile")
                .and()
            .authorizeRequests()
                .antMatchers("/account/inquiry").access("(#oauth2.isClient() and #oauth2.hasScope('ACCOUNT_INQUIRY')) or " + IS_NOT_OAUTH_USER)
                .antMatchers("/account/transfer").access("(#oauth2.isClient() and #oauth2.hasScope('ACCOUNT_TRANSFER')) or" + IS_NOT_OAUTH_USER)
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("account").stateless(false).tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(new JwtAccessTokenConverter());
    }
}
package openapi.bank.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author chanwook
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("bank-user01").password("1234").roles("USER").and()
                .withUser("bank-user02").password("1234").roles("ADMIN").and()
                .withUser("bank-user03").password("1234").roles("GUEST");
    }
/*
    @Autowired
    public void defaultUserDetails(AuthenticationManagerBuilder builder) throws Exception {
        builder
            .inMemoryAuthentication()
                .withUser("bank-user01").password("1234").roles("USER").and()
                .withUser("bank-user02").password("1234").roles("ADMIN").and()
                .withUser("bank-user03").password("1234").roles("GUEST")
        ;
    }
*/
}

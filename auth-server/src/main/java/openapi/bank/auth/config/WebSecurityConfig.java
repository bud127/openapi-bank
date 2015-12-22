package openapi.bank.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author chanwook
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void defaultUserDetails(AuthenticationManagerBuilder builder) throws Exception {
        builder
            .inMemoryAuthentication()
                .withUser("bank-user01").password("1234").roles("USER").and()
                .withUser("bank-user02").password("1234").roles("ADMIN").and()
                .withUser("bank-user03").password("1234").roles("GUEST")
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login.bank").permitAll()
                .anyRequest().hasAnyRole("USER")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/login.bank?_error=true")
                .and()
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
            .formLogin()
                .loginPage("/login.bank")
                .loginProcessingUrl("/login")
                .failureUrl("/login.bank?_error=true")
                .defaultSuccessUrl("/main.bank")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.bank?_logout=true")
        ;
    }
}

package openapi.bank.auth;

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
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //FIXME Caused by: org.springframework.security.config.annotation.AlreadyBuiltException: This object has already been built

    @Autowired
    public void defaultUserDetails(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user01").password("1234").roles("USER").and()
                .withUser("user02").password("1234").roles("ADMIN").and()
                .withUser("user03").password("1234").roles("GUEST")
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/oauth/**", "**/favicon.ico", "/css/**", "/js/**");
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
                .anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
            .formLogin()
                .loginPage("/login.bank")
                .loginProcessingUrl("/login")
                .failureUrl("/login.bank?_error=true")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.bank?_logout=true")
        ;
    }
}

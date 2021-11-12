package ir.maktab.project.config;

import ir.maktab.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().defaultSuccessUrl("/login-user").failureUrl("/login?error=true");
        http.authorizeRequests().mvcMatchers("/master/**").hasRole("master")
        .mvcMatchers("/course/**").hasRole("manager");
        http.authorizeRequests().mvcMatchers("/unauthorized").authenticated();
        http.exceptionHandling().accessDeniedPage("/unauthorized");
        http.cors().and().csrf().disable();
        http.formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}

package ir.maktab.project.config;

import ir.maktab.project.filter.RecaptchaFilter;
import ir.maktab.project.service.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RecaptchaService recaptchaService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(getUsernamePasswordAuthenticationFilter(), RecaptchaFilter.class).formLogin().loginPage("/login");
        http.authorizeRequests().mvcMatchers("/", "", "/login", "/resource/**").permitAll();
        http.authorizeRequests().mvcMatchers("/master/**").hasRole("master")
                .mvcMatchers("/student/**").hasRole("student")
                .mvcMatchers("/course/**").hasRole("manager");
        http.authorizeRequests().mvcMatchers("/WEB-INF/views/accessDenied.jsp").authenticated();
        http.exceptionHandling().accessDeniedPage("/WEB-INF/views/accessDenied.jsp");
        http.cors().and().csrf().disable();
        http.formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() throws Exception {

        RecaptchaFilter filter = new RecaptchaFilter(recaptchaService);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"));
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/login-user"));
        return filter;
    }
}

package com.codeup.stackknot;

import com.codeup.stackknot.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/sets", "/sets/{id: [\\d+]}",
                        "/subjects", "/subjects/{id: [\\d+]}", "/subjects/{title}",
                        "/cards/create/{id: [\\d+]}",
                        "/about-us", "/contact-form", "/search", "/sign-up",
                        "/whiteboard", "/whiteboardStart", "/whiteboard/{id: [\\d+]}", "/whiteboard-next"
                        )
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/sets/create",
                        "/sets/{id: [\\d+]}/edit", "/sets/{id: [\\d+]}/delete",
                        "/cards/{id: [\\d+]}/edit", "/cards/{id}/delete",
                        "/tests/{id: [\\d+]}", "/tests/{id: [\\d+]}/grade",
                        "/profile/{username}", "/profile/{id: [\\d+]}/edit",
                        "/whiteboard/upload"
                )
                .authenticated()
        ;
    }
}

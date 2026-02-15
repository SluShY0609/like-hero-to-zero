package com.example.likeherotozero.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/emission", "/login", "/css/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        .requestMatchers("/scientist/**").hasRole("SCIENTIST")
                        .requestMatchers("/publisher/**").hasRole("PUBLISHER")

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(this::loginSuccessHandler)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    private void loginSuccessHandler(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Authentication authentication)
            throws IOException, ServletException {

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_PUBLISHER"))) {
            response.sendRedirect("/publisher/pending");
        } else if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_SCIENTIST"))) {
            response.sendRedirect("/scientist/dashboard");
        } else {
            response.sendRedirect("/");
        }
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails scientist = User.withDefaultPasswordEncoder()
                .username("scientist")
                .password("secret")
                .roles("SCIENTIST")
                .build();

        UserDetails publisher = User.withDefaultPasswordEncoder()
                .username("publisher")
                .password("secret")
                .roles("PUBLISHER")
                .build();

        return new InMemoryUserDetailsManager(scientist, publisher);
    }
}

package com.example.likeherotozero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Für die H2-Console: Frames erlauben + CSRF für diesen Pfad aus
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

                .authorizeHttpRequests(auth -> auth
                        // öffentliche Seiten (ohne Login)
                        .requestMatchers("/", "/country", "/emission", "/css/**").permitAll()

                        // H2-Console optional öffentlich (nur lokal!):
                        .requestMatchers("/h2-console/**").permitAll()

                        // Backend nur für Scientists
                        .requestMatchers("/scientist/**").hasRole("SCIENTIST")

                        // alles andere: login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")               // wir erstellen später eine eigene login.html
                        .defaultSuccessUrl("/scientist/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails scientist = User.withDefaultPasswordEncoder()
                .username("scientist")
                .password("secret")
                .roles("SCIENTIST")
                .build();

        return new InMemoryUserDetailsManager(scientist);
    }
}

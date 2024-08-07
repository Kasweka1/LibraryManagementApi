package com.example.LibraryManagmentApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.LibraryManagmentApi.security.restapi.jwtconfig.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/ticketTypes").permitAll()
                        .requestMatchers("/api/ticketPrice").permitAll()
                        .requestMatchers("/api/tickets/**").permitAll()
                        .requestMatchers("/wallet/users/search").permitAll()// come back to this one 
                        .requestMatchers("/login", "/resources/**", "/assets/**", "/bootstrap/**", "/sass/**", "/plugins/**", "/img/**").permitAll()
                        .requestMatchers("/register", "/resources/**", "/assets/**", "/bootstrap/**", "/sass/**", "/plugins/**", "/img/**").permitAll()
                        .requestMatchers("/ticket/**").hasAnyAuthority("ADMIN", "ACCOUNTANT")
                        .requestMatchers("/report/**").hasAnyAuthority("ADMIN", "ACCOUNTANT")
                        .requestMatchers("/wallet/**").hasAnyAuthority("ADMIN", "ACCOUNTANT")
                        .requestMatchers("/security/**").hasAuthority("ADMIN")
                        .requestMatchers("/index").hasAnyAuthority("ADMIN", "ACCOUNTANT")// come back to this one
                        .requestMatchers("/updatedData").permitAll()
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .formLogin(login -> login  // Add formLogin() configuration here
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/index"))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout  // Continue with the existing configuration
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login").permitAll())
                .exceptionHandling(handling -> handling.accessDeniedPage("/accessDenied"));

    

    return http.build();
}
}
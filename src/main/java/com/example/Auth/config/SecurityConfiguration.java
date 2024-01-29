package com.example.Auth.config;

import com.example.Auth.entities.Role;
import com.example.Auth.repository.UserRepository;
import com.example.Auth.services.impl.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

// import lombok.AllArgsConstructor;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    request -> {
                        request.requestMatchers("/api/farmer/**").hasAuthority(Role.FARMER.name());
                        request.requestMatchers("/api/customer/**").hasAuthority(Role.CUSTOMER.name());
                        request.requestMatchers("/api/**").permitAll();
                        request.anyRequest().authenticated();
    })
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                

        return http.build();


        // http
        //         .csrf(AbstractHttpConfigurer::disable)
        //         .authorizeHttpRequests(auth -> auth
        //                 .requestMatchers("/**").permitAll()
        //         )
        //         .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // return http.build();

    }

    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //     authProvider.setUserDetailsService(userDetailsServiceImpl);
    //     authProvider.setPasswordEncoder(passwordEncoder());
    //     return authProvider;
    // }

}

package com.example.todobackend.config;

import com.example.todobackend.auth.AuthEntryPoint;
import com.example.todobackend.auth.AuthenticationFilter;
import com.example.todobackend.auth.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

@EnableMethodSecurity
@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationFilter authenticationFilter;
    private final AuthEntryPoint exceptionHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthenticationFilter authenticationFilter, AuthEntryPoint exceptionHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationFilter = authenticationFilter;
        this.exceptionHandler = exceptionHandler;
    }

    public void configureGlobal (AuthenticationManagerBuilder auth)
            throws Exception  {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(exceptionHandler));
//        return http
//                .httpBasic(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .headers(AbstractHttpConfigurer::disable)
//                .sessionManagement(ma -> ma.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()
//                        .anyRequest().denyAll())
        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:5173");
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new
//                UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
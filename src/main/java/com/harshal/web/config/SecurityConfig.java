package com.harshal.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //this annotation tells spring that this class is a configuration class, and it will be used to configure the application.
@EnableWebSecurity //this annotation tells spring that don't follow your default security filter chain and use the custom security chain defined in this class.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //this method is used to configure the security filter chain.

        //all things are written with same http object (this type of writing is called method chaining).
        return http
                .csrf((customizer)->customizer.disable()) //this line is used to disable the csrf token.
                .authorizeHttpRequests((request) -> request.anyRequest().authenticated()) //this line is used to authorize all the http requests.
                .httpBasic(Customizer.withDefaults()) //this line is used to enable form based login for the basic http protocol like postman.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //this line is used to disable the session creation in the server and tells the http protocol to go stateless i.e. do not store sessions across different requests.
                        .build();

//        http.formLogin(Customizer.withDefaults()); //this line is used to enable form based login for application interface like web browsers.
    }
}

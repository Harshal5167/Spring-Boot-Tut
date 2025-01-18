package com.harshal.web.Config;

import com.harshal.web.Service.customUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //this annotation tells spring that this class is a configuration class, and it will be used to configure the application.
@EnableWebSecurity //this annotation tells spring that don't follow your default security filter chain and use the custom security chain defined in this class.
@RequiredArgsConstructor //this annotation is used to tell the spring that create a constructor with all the final fields.
public class SecurityConfig {

    private final customUserDetailsService userDetailsService; //custom user details class.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //this method is used to configure the security filter chain of our own rather than the default one.

        //all things are written with same http object (this type of writing is called method chaining).
        return http
                .csrf(AbstractHttpConfigurer::disable) //this line is used to disable the csrf token.
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/api/user/login", "/api/user/register").permitAll() //this line is used to permit the login and register routes without authentication
                        .anyRequest().authenticated()) //this line is used to authorize all other http requests.
                .httpBasic(Customizer.withDefaults()) //this line is used to enable form based login for the basic http protocol like postman.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //this line is used to disable the session creation in the server and tells the http protocol to go stateless i.e. do not store sessions across different requests.
                .build();

//        http.formLogin(Customizer.withDefaults()); //this line is used to enable form based login for application interface like web browsers.
    }

    @Bean
    public AuthenticationProvider authenticationProvider() { //this method is used to create the authentication provider which will be used to authenticate the user. it gives us the flexibility to create user in database and validate form there.
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //this line is used to tell the spring that use the BCryptPasswordEncoder to check the password.
        provider.setUserDetailsService(userDetailsService); //this line is used to tell the spring that use the custom user details service to authenticate the user.
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){ //this method is used to create the user details service which will be used to authenticate the user (hard coded) not recommended.
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("user1")
//                .password("1234")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("user2")
//                .password("1234")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    };
}

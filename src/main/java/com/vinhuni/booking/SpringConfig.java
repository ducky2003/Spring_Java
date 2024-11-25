package com.vinhuni.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((auth)->auth.
//                requestMatchers("/*").permitAll().
//                requestMatchers("/admin/**").hasAuthority("ADMIN").
//                anyRequest().authenticated()).formLogin(login-> login.loginPage("/logon").loginProcessingUrl("/logon")
//                .usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/admin",true)
//        );
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll() // Cho phép tất cả yêu cầu mà không cần xác thực
            );
    return http.build();
}
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web)->web.ignoring().requestMatchers("/static/**","/fe/**", "/assets/**");
    }
}

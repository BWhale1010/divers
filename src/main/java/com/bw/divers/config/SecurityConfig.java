package com.bw.divers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
//                .antMatchers("/manage/userRole/**", "/manage/userState/**").hasRole("SUPER")
//                .antMatchers("/manage/**").hasAnyRole("SUPER", "ADMIN")
//                .antMatchers("/mypage/**").authenticated()
                .antMatchers("/assets/**", "/", "/user/**", "/board/list/**", "/board/detail/**").permitAll()
  
                .and()
            .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/loginProc")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
            .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
            .csrf().disable()
            .httpBasic().disable();
        
        return http.build();
    }
    
}

package com.bw.divers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthProvider authProvider;
	
	@Autowired
	private LoginHandler loginHandler;
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired HttpSessionRequestCache requestCache;
	
	@Bean
	public HttpSessionRequestCache requestCache() {
		return new HttpSessionRequestCache();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		.antMatchers("/manage/reportWrite", "/manage/reportPostCheck", "/manage/reportCommentCheck").permitAll()
                .antMatchers("/manage/userRole", "/manage/userState").hasRole("SUPER")
                .antMatchers("/manage/**").hasAnyRole("SUPER", "ADMIN")
                .antMatchers("/mypage/withDraw").hasRole("USER")
                .antMatchers("/mypage/**", "/board/write", "/board/edit/{postNum}", "/manage/reportWrite", "/manage/reportPostCheck", "/manage/reportCommentCheck").authenticated()
                .antMatchers("/board/delete/{postNum}", "/comment/write", "/comment/delete/{comment_num}").authenticated()
                .antMatchers("/comment/edit/{comment_num}", "/board/thumb/{postNum}", "/comment/thumb/{comment_num}", "/manage/reportWrite").authenticated()
                .antMatchers( "/board/list/**", "/board/detail/**", "/board/listAdd", "/board/thumbnail/{postNum}").permitAll()
                .antMatchers("/comment/list", "/board/recommendList", "/board/popList", "/board/newList").permitAll()
                .antMatchers("/assets/**", "/", "/main/**", "/user/**").permitAll()
                .and()
            .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/loginProc")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginHandler).permitAll()
                .and()
            .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
            .csrf().disable()
            .httpBasic().disable()
            ;
        
        return http.build();
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authProvider);
    }
    


    
}

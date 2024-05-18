package com.bw.divers.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bw.divers.user.UserDTO;
import com.bw.divers.user.UserService;

@Component
public class AuthProvider implements AuthenticationProvider{
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
	   @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		UsernamePasswordAuthenticationToken token;
		UserDTO userDTO = userService.getUserbyUsername(username);
		int role = userDTO.getRole_num();
		logger.info("role num : "+role);
		
		if(userDTO != null && passwordEncoder.matches(password, userDTO.getPassword())) {
			List<GrantedAuthority> roles = new ArrayList<>();
			if(role == 1) {
				roles.add(new SimpleGrantedAuthority("ROLE_SUPER"));
			}else if(role == 2) {
				roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}else {
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			}

			token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), null, roles);
			
			return token;
		}else {
			return null;
		}
			

		
	}
	
	public boolean supports(Class<?> authentication) {
		
		return true;
	}

}

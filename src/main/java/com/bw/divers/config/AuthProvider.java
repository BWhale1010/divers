package com.bw.divers.config;

import java.util.ArrayList;
import java.util.List;

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
	
	
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
//		PasswordEncoder passwordEncoder = userService.passwordEncoder();
		UsernamePasswordAuthenticationToken token;
		UserDTO userDTO = userService.getUserbyUsername(username);
		
		if(userDTO != null && passwordEncoder.matches(password, userDTO.getPassword())) {
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority("USER"));
			
			token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), null, roles);
			
			return token;
		}
		
		throw new BadCredentialsException("유저가 없거나 비밀번호가 틀립니다.");
		
	}
	
	public boolean supports(Class<?> authentication) {
		
		return true;
	}

}

package com.Pratham.UserService.auth;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Pratham.UserService.Model.UserModel;
import com.Pratham.UserService.Repositiory.UserRepo;

@Component
public class DbAuthenticationProvider implements AuthenticationProvider {
	
	
	UserRepo uRepo;
	
	
	PasswordEncoder pe;
	
	DbAuthenticationProvider(UserRepo uRepo,PasswordEncoder pe){
		this.uRepo=uRepo;
		this.pe=pe;
	}
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		UserModel um=uRepo.findByName(username);
		
		if(um==null || !um.getName().equals(username)) {
			throw new BadCredentialsException("no username isfound");
		}else if(!pe.matches(password,um.getPass())) {
			throw new BadCredentialsException("no password not matched");
		}else {
			
			Set<GrantedAuthority> authorities=Collections.singleton(new SimpleGrantedAuthority(um.getRole()));
			
			return new UsernamePasswordAuthenticationToken(username,null,authorities);
		}
		
		
		
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}

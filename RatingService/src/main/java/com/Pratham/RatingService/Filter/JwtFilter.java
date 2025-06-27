package com.Pratham.RatingService.Filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.customsec.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String header = request.getHeader("Authorization");
		String token = "";
		String username = "";
		
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
try {
			username = jwtUtil.extractUsername(token);
			

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
				 if(jwtUtil.isValid(token)) {

					String role= jwtUtil.extractRole(token);
					 
					  Set<GrantedAuthority> authority=Collections.singleton(new SimpleGrantedAuthority(jwtUtil.extractRole(token)));
					  
					  UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username,null,authority);
					  
					  SecurityContextHolder.getContext().setAuthentication(authToken);
					  
					  
				  }

			}
}catch (Exception e) { // Catch JWT parsing/validation exceptions
    // Critical: Send error response and STOP processing
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
    return; // Exit the filter chain
}

		}
		
		
		filterChain.doFilter(request, response);
	}

}

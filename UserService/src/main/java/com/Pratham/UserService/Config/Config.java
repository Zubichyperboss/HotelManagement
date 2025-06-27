package com.Pratham.UserService.Config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Pratham.UserService.Filter.JwtFilter;



@Configuration
@EnableWebSecurity
public class Config {
	
	JwtFilter jwtFilter;	
	Config(JwtFilter jwtFilter){
		this.jwtFilter=jwtFilter;
	}

	 @Bean
	    SecurityFilterChain sfc(HttpSecurity http,AuthenticationProvider authProvider) throws Exception {
			
			http.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(req->req.requestMatchers("/user/register","/user/login","/user/registerMultiple").permitAll().anyRequest().authenticated())
			//.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults())
			.authenticationProvider(authProvider) //this one
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		//	.addFilterBefore(jwtF,UsernamePasswordAuthenticationFilter.class);
			
			return http.build();
		}
	 
	 
	 
	 
	 
	 

	    

	
}

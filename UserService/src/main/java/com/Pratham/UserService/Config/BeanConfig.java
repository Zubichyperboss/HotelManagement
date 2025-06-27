package com.Pratham.UserService.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Pratham.UserService.Interceptor.FeignAuthInterceptor;
import com.security.customsec.JwtUtil;

import feign.RequestInterceptor;

@Configuration
public class BeanConfig {
	
	@Bean
    PasswordEncoder paswordEncoder() {
    	return new BCryptPasswordEncoder(12);
    }
	
	@Value("${jwt.current-key}")
    private String secretKey;

	@Bean
    JwtUtil jwtUtil() {
       return new JwtUtil(secretKey);
   }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationProvider authProvider){
        return new ProviderManager(List.of(authProvider));
    }
    
    @Bean
     RequestInterceptor feignAuthInterceptor() {
        return new FeignAuthInterceptor(); // implements RequestInterceptor
    }
}

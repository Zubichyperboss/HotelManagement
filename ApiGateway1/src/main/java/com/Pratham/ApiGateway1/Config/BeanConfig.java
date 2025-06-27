package com.Pratham.ApiGateway1.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.security.customsec.JwtUtil;

@Configuration
public class BeanConfig {

	@Value("${jwt.current-key}")
    private String secretKey;

    @Bean
     JwtUtil jwtUtil() {
        return new JwtUtil(secretKey);
    }

	
}

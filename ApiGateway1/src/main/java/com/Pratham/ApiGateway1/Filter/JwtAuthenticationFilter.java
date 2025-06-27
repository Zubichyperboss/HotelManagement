package com.Pratham.ApiGateway1.Filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.security.customsec.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {
	
JwtUtil jwtUtil;
	
	JwtAuthenticationFilter(JwtUtil jwtUtil){
		this.jwtUtil=jwtUtil;
	}
	

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
	ServerHttpRequest request=exchange.getRequest();
		
		List<String> openEndpoints = List.of("/user/login", "/user/register");

		//so see whats happeing over here we do not need this rn as we are already sikking this filter in application.properties for user
		// howevere in case if we in future apply this filter for user service then we need this line  in oder to bypass
		//user login and register endpoints as we dont have authorization for those i mean login and regigester ke liyeb 
		//login kyu karna so thats why its a future check.  
		if (openEndpoints.contains(request.getURI().getPath())) {
		    return chain.filter(exchange);
		}

		
		
		
		
		String header=request.getHeaders().getFirst("Authorization");
		String token="";
		
		if(header!=null && header.startsWith("Bearer ")) {
			token=header.substring(7);
		}else {
			token=null;
		}
		
		if(token == null || !jwtUtil.isValid(token)) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}else {
			return chain.filter(exchange);
		}
		

	}

}

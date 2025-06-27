package com.Pratham.ApiGateway1.FilterFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.Pratham.ApiGateway1.Filter.JwtAuthenticationFilter;


@Component
public class JwtAuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

	@Autowired
	JwtAuthenticationFilter  jwtFilter;
	
	public JwtAuthenticationGatewayFilterFactory() {
		super(Object.class);
	}
	
	
	@Override
	public GatewayFilter apply(Object config) {
		// TODO Auto-generated method stub
		return jwtFilter;
	}

}

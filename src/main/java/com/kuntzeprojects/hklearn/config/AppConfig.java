package com.kuntzeprojects.hklearn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAccessTokenConverter converter() {
		JwtAccessTokenConverter tc = new JwtAccessTokenConverter();
		tc.setSigningKey(secret);
		return tc;
	}
	
	@Bean
	public JwtTokenStore store() {
		return new JwtTokenStore(converter());
	}
}

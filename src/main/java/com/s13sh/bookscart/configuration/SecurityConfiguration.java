package com.s13sh.bookscart.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private LogoutHandler logoutHandler;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(req -> req.disable());

		httpSecurity.authorizeHttpRequests(
				req -> req.requestMatchers("/", "/error", "/logout", "/signin", "/signup", "/products", "/**")
						.permitAll().anyRequest().authenticated());

		httpSecurity.formLogin(login -> login.loginPage("/signin").loginProcessingUrl("/login")
				.successHandler(authenticationSuccessHandler));

		httpSecurity.logout(logout -> logout.addLogoutHandler(logoutHandler));

		return httpSecurity.build();
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService detailsService() {
		return new CustomUserDetailService();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder());
		provider.setUserDetailsService(detailsService());
		return provider;
	}
}

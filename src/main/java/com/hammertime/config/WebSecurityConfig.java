package com.hammertime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hammertime.hammertime2.domain.login.HTLoginService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public HTLoginService userDetailsService(){
		return new HTLoginService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();

		http
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/main", "/static/**", "/signup", "/dataRequest/**", "/subscription", "/payments", "/account").permitAll()
				.requestMatchers("/tradie/**").hasAuthority("ROLE_TRADIE")
				.requestMatchers("/client/**").hasAuthority("ROLE_CLIENT")
				.requestMatchers("/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
			)
			
		.formLogin((form) -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.successHandler((request, response, authentication) -> {
					if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TRADIE"))) {
						response.sendRedirect("/tradie/home");
					} else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
						response.sendRedirect("/client/home");
					} else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
						response.sendRedirect("/admin/home");
					} else {
						response.sendRedirect("/");
					}
			})
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

			return http.build();
	}
	
}




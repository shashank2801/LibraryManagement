package com.shashank.LMS.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private UserServiceImpl userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http.authorizeHttpRequests(request -> request
				.requestMatchers("/public/**").permitAll()
				.requestMatchers("/author/**").hasRole("ADMIN")
				.requestMatchers("/book/createBook").hasRole("ADMIN")
				.requestMatchers("/book/**").hasAnyRole("STUDENT","ADMIN")
				
				.requestMatchers(HttpMethod.PUT,"/student/**").hasAnyRole("STUDENT","ADMIN")
                .requestMatchers(HttpMethod.POST,"/student/**").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET,"/student/").hasRole("STUDENT")
                .requestMatchers("/student/**").hasRole("ADMIN")


                .requestMatchers(HttpMethod.POST,"/transaction/**").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET,"/transaction/").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET,"/transaction/all").hasRole("ADMIN")

				.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.csrf(AbstractHttpConfigurer::disable)
			.build();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
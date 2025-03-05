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
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/author/**").hasAnyRole("ADMIN","LIBRARIAN")
				.requestMatchers("/book/createBook").hasAnyRole("ADMIN","LIBRARIAN")
				.requestMatchers("/book/**").hasAnyRole("STUDENT","ADMIN","LIBRARIAN")
				.requestMatchers("/card/**").hasAnyRole("ADMIN","LIBRARIAN")
				.requestMatchers("/student/**").hasAnyRole("ADMIN","LIBRARIAN")
				.requestMatchers("/transaction/**").hasAnyRole("ADMIN","LIBRARIAN")
				
				
//				.requestMatchers(HttpMethod.PUT,"/student/**").hasAnyRole("LIBRARIAN","ADMIN")
//                .requestMatchers(HttpMethod.POST,"/student/**").hasAnyRole("LIBRARIAN","ADMIN")
//                .requestMatchers(HttpMethod.GET,"/student/**").hasAnyRole("LIBRARIAN","ADMIN")
                .requestMatchers("/student/**").hasAnyRole("ADMIN","LIBRARIAN")


                .requestMatchers(HttpMethod.POST,"/transaction/**").hasAnyRole("STUDENT","ADMIN","LIBRARIAN")
                .requestMatchers(HttpMethod.GET,"/transaction/byCard/**").hasAnyRole("STUDENT","LIBRARIAN")
                .requestMatchers(HttpMethod.GET,"/transaction/all").hasAnyRole("ADMIN","LIBRARIAN")

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
package com.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.user.jwt.Filter;

@EnableWebSecurity
@Configuration

public class Security {
	
	@Autowired
	public UserService userservice;
	
	@Autowired
	public Filter filter;
	
	@Bean
    AuthenticationProvider authenticateprovider() {
       
       DaoAuthenticationProvider authprovider= new DaoAuthenticationProvider();
       authprovider.setUserDetailsService(userservice);
       authprovider.setPasswordEncoder(new BCryptPasswordEncoder());
       return authprovider;
       
   }
	
    @Bean
    SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
        
        http.csrf().disable().cors().disable()
        .authorizeHttpRequests()
        .requestMatchers("/registration")
        .permitAll()
        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
        .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
        
    }
   @Bean
    AuthenticationManager authmanager(AuthenticationConfiguration config) throws Exception {
        
        return config.getAuthenticationManager();
    }

  
}




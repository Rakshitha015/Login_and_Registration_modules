package com.example.security_login_reg1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//@Bean annotation is used to define a Spring Bean named bCryptPasswordEncoder that returns a new instance of BCryptPasswordEncoder. 
//This bean can then be used throughout your application for password encoding and decoding.

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
/*
The UserDetailsService interface is commonly used in Spring Security for loading 
user details during the authentication process. By declaring this bean, you provide 
a custom implementation (CustomUserDetailsService) that can load user details based on your application's requirements.

*/    
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new CustomUserDetailsService();
    }

/*
 * The HttpSecurity class in Spring Security is used for configuring security settings for 
 * HTTP requests. It provides a fluent API for defining how your application handles security, 
 * including aspects such as authorization, authentication, and other security-related features.
 * The HttpSecurity object is typically configured in a configure(HttpSecurity http) method within a 
 * class that extends WebSecurityConfigurerAdapter.
 */
  
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/", "/register", "/signin", "/saveUser").permitAll()
                        .requestMatchers("/user/**").authenticated())
                .formLogin(login -> login.loginPage("/signin").loginProcessingUrl("/userLogin")
                        //.usernameParameter("email")
                        .defaultSuccessUrl("/user/profile").permitAll());
		return http.build();
	}
}

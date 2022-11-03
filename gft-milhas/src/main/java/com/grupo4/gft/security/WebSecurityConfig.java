package com.grupo4.gft.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.grupo4.gft.servicies.UserService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	  public UserDetailsService userDetailsService()  {
	   return new UserService();
		
	   }

	 @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	  }

	  @Bean
	  
	  public DaoAuthenticationProvider authenticationProvider() {
		  DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		  
		  authProvider.setUserDetailsService(userDetailsService());
		  authProvider.setPasswordEncoder(passwordEncoder());
		  
		return authProvider;
	  }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		.antMatchers("/group/ranking.html").permitAll()
		.antMatchers("/user/new").permitAll()
		.antMatchers( "/activit/new").authenticated()
		.antMatchers( "/activit/edit").authenticated()
		.antMatchers( "/activit/list").authenticated()
		.antMatchers( "/activit/delete").authenticated()
		.antMatchers( "/event/edit").authenticated()
		.antMatchers( "/event/list").authenticated()
		.antMatchers( "/event/delete").authenticated()
		.antMatchers( "/group/edit").authenticated()
		.antMatchers( "/group/delete").authenticated()
		.antMatchers( "/group//removeGuest").authenticated()
		.antMatchers( "/guest/edit").authenticated()
		.antMatchers( "/guest/list").authenticated()
		.antMatchers( "/guest/delete").authenticated()
		.antMatchers( "/attendance/edit").authenticated()
		.antMatchers( "/attendance/delete").authenticated()
		
		.and()
		.formLogin()
			.permitAll()
			.loginPage("/login")
			.usernameParameter("email")
			.passwordParameter("motdepass")
			.defaultSuccessUrl("/event/list")
			.loginProcessingUrl("/doLogin")
			.failureUrl("/login_error")
		.and()
		.logout()
		.permitAll()
		.logoutUrl("/group/ranking")
		.permitAll();
		
		
		
//		.antMatchers("/", "/group/ranking.html","/guest").permitAll() 
//		.antMatchers("/user/new").permitAll()
//		.antMatchers("/delete/**").hasAnyAuthority("ADMIN")
//		.antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//			.permitAll()
//			.loginPage("/login")
//			.usernameParameter("email")
//			.passwordParameter("motdepass")
//			.defaultSuccessUrl("/event/list")
//			.loginProcessingUrl("/doLogin")
//			.failureUrl("/login_error")
//		.and()
//		.logout()
//		.permitAll()
//		.invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutSuccessUrl("/doLogin?logout")
//		.logoutUrl("/Logout")
//		.logoutSuccessUrl("/group/ranking.html")
//		.permitAll();
		
	}

}

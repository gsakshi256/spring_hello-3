package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class EmployeeSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	// public final Integer SESSION_TIMEOUT_IN_SECONDS = 60 * 10;
	//
	// public void onAuthenticationSuccess(HttpServletRequest request,
	// HttpServletResponse response,
	// Authentication authentication) throws ServletException, IOException {
	//
	// request.getSession().setMaxInactiveInterval(600);
	// }

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
		// auth.inMemoryAuthentication().withUser("user").password("userPass").authorities("ROLE_USER");
		// auth.inMemoryAuthentication().withUser("admin").password("adminpass").authorities("ADMIN");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/uploadfile").permitAll().antMatchers("/role/tokengenerate")
				.permitAll().antMatchers("/role/validatetoken").permitAll()
				.antMatchers("/mail").permitAll().antMatchers("/role/addnewuser")
				.permitAll().antMatchers("/uploadfile").permitAll().antMatchers("/role/setpassword")
				.hasAnyAuthority("ROLE_USER1", "ADMIN", "ROLE_USER2").antMatchers("/userall").hasAnyAuthority("ROLE_USER1", "ADMIN", "ROLE_USER2")
				.anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);

		// .addFilterAfter(new CorsFilter(), BasicAuthenticationFilter.class);

	}

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

}

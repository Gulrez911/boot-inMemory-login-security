package com.ctet;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());

//		return authProvider;
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication

		@SuppressWarnings("deprecation")
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(users.username("john").password("john").roles("EMPLOYEE"))
				.withUser(users.username("mary").password("mary").roles("MANAGER"))
				.withUser(users.username("susan").password("susan").roles("ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.authorizeRequests().antMatchers("/", "/test", "/login", "/oauth/**").permitAll().
//		anyRequest()
//				.authenticated()
//		.and().oauth2Login().
//		defaultSuccessUrl("/login_success")
//		.failureUrl("/login_failure");

//		http.authorizeRequests()
//		.antMatchers("/**").permitAll().and()
//			.formLogin()
//				.loginPage("/showMyLoginPage")
//				.defaultSuccessUrl("/authenticateTheUser")
//				.failureUrl("/showMyLoginPage")
//				.permitAll()
//			.and()
//			.logout().permitAll();

//		http.authorizeRequests()
//		.antMatchers("/showMyLoginPage").permitAll()
//		.and()
//		.formLogin().loginPage("/showMyLoginPage")
//				.failureUrl("/showMyLoginPage?error=true").and().logout().permitAll();

        http
        .authorizeRequests()
            .antMatchers("/resources/**", "/WEB-INF/views/*").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
//            .loginPage("/showMyLoginPage")
            .defaultSuccessUrl("/welcome")
            .failureUrl("/login?error")
//            .failureUrl("/showMyLoginPage?error")
            .permitAll()
            .and()
        .logout()
            .permitAll();
	}

}

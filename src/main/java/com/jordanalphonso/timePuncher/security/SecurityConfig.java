package com.jordanalphonso.timePuncher.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ImportResource("/WEB-INF/spring/appServlet/servlet-context.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserAuthService<GrantedAuthority> userAuthService;
	@Autowired
	private TokenAuthService tokenAuthService;
	@Autowired
	private TokenHandler tokenHandler;

	private static String secret = "r3hcnup3m1t";
	
	public SecurityConfig(){
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.exceptionHandling().and()
			.anonymous().and()
			.servletApi().and()
			.headers().and()
			.authorizeRequests()
			
			.antMatchers("/").permitAll()
			.antMatchers("**/*.html").permitAll()
			.antMatchers("**/*.css").permitAll()
			.antMatchers("**/*.js").permitAll()
			
			.antMatchers("/api/login").permitAll()
			.antMatchers("/api/login.json").permitAll()
			.antMatchers("/api/login.xml").permitAll()
			
			.anyRequest().authenticated().and()
			
			.addFilterBefore(new AuthFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class);
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(userAuthService);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}
	
	@Override
	@Bean
	public UserAuthService<GrantedAuthority> userDetailsService(){
		
		userAuthService = new UserAuthService<GrantedAuthority>();
		return userAuthService;
	}
	
	@Bean
	public TokenAuthService tokenAuthService(){
		tokenAuthService = new TokenAuthService(secret, userAuthService);
		return tokenAuthService;
	}
	
	@Bean
	public TokenHandler tokenHandler(){
		tokenHandler = new TokenHandler(secret, userAuthService);
		return tokenHandler;
	}
}

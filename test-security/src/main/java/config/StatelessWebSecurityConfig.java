package config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import stateless.MockTokenAuthFilter;

@EnableWebMvcSecurity
@Configuration
public class StatelessWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(new MockTokenAuthFilter(), BasicAuthenticationFilter.class)
		.exceptionHandling()
			.authenticationEntryPoint(forbiddenEntryPoint())
			.and()
		.exceptionHandling()
			.accessDeniedHandler(nonRedirectingAccessDeniedHandler())
			.and()
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .securityContext()
            .securityContextRepository(securityContextRepository())
            .and()
        .httpBasic();
	}
	
	@Bean
    public SecurityContextRepository securityContextRepository() {
        HttpSessionSecurityContextRepository repo = new HttpSessionSecurityContextRepository();
        repo.setSpringSecurityContextKey("CUSTOM");
        return repo;
    }
	
	@Bean
	public AuthenticationEntryPoint forbiddenEntryPoint(){
		return new Http403ForbiddenEntryPoint();
	}
	
	@Bean
	public AccessDeniedHandler nonRedirectingAccessDeniedHandler(){
		return new AccessDeniedHandlerImpl();
	}
}


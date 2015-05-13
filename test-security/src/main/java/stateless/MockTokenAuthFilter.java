package stateless;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;


public class MockTokenAuthFilter extends GenericFilterBean {
	
	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		UserDetails user = getDetailsFromToken((HttpServletRequest) request);
		if(user != null){
			SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(user));
		}
		chain.doFilter(request, response); // always continue
	}
	
	private UserDetails getDetailsFromToken(HttpServletRequest request){
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			return new User("user", "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		}
		else{
			return null;
		}
	}

}

package test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import stateless.MockTokenAuthFilter;
import config.StatelessWebSecurityConfig;
import config.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WebMvcConfig.class, StatelessWebSecurityConfig.class})
@WebAppConfiguration
public class TestSecureStateless {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
    private Filter springSecurityFilterChain;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
	}
	
	@Test
	public void testUnsecureRequest() throws Exception{
		mvc.perform(get("/test")).andExpect(status().isForbidden());
	}
	
	@Test
	public void testUnsecureRequestWithUser() throws Exception{
		mvc.perform(get("/test").header(MockTokenAuthFilter.AUTH_HEADER_NAME, "mockHeaderValue")).andExpect(status().is(200));
	}

	
	public static RequestPostProcessor auth() {
		return user("user").roles("USER");
	}
}

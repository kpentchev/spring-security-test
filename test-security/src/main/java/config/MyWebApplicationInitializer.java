package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

	public class WebMvcAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
		@Override
		protected Class<?>[] getRootConfigClasses() {
			return null;
		}

		@Override
		protected Class<?>[] getServletConfigClasses() {
			return new Class[] {WebMvcConfig.class};
		}

		@Override
		protected String[] getServletMappings() {
			return new String[] { "/"};
		}
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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
			return new String[] {"/"};
		}
	}
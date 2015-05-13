package config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class StatelessSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    public StatelessSecurityApplicationInitializer() {
        super(StatelessWebSecurityConfig.class);
    }
}

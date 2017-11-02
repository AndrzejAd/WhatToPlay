
package whattoplay.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import whattoplay.services.UserAuthService;
import whattoplay.config.rest.RESTAuthenticationEntryPoint;
import whattoplay.config.rest.RESTAuthenticationFailureHandler;
import whattoplay.config.rest.RESTAuthenticationSuccessHandler;

/**
 *
 * @author Andrzej
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        private RESTAuthenticationEntryPoint authenticationEntryPoint;
        private RESTAuthenticationFailureHandler authenticationFailureHandler;
        private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
        private UserAuthService userAuthService;
        
        @Autowired
        public SecurityConfig(RESTAuthenticationEntryPoint authenticationEntryPoint, RESTAuthenticationFailureHandler authenticationFailureHandler,
                              RESTAuthenticationSuccessHandler authenticationSuccessHandler) {
            this.authenticationEntryPoint = authenticationEntryPoint;
            this.authenticationFailureHandler = authenticationFailureHandler;
            this.authenticationSuccessHandler = authenticationSuccessHandler;
        }
        
        @Autowired
        public void setUserAuthService(UserAuthService userAuthService) {
            this.userAuthService = userAuthService;
        }
        
        
        
        
        
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/home", "/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/addGame").hasRole("USER")
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
            .and()
                .logout();
            
	}
     
       
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setPasswordEncoder(encoder());
            authProvider.setUserDetailsService(userAuthService);
            return authProvider;
        }

        @Bean
        public PasswordEncoder encoder() {
            return new BCryptPasswordEncoder(11);
        }
        
}
